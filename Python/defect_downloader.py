import requests
import re
import json
import getpass
import errno
import os
import threading
import queue
import lxml.etree
import sys
import glob
import io

SERIALIZATION_METHOD = 'xml'
PRETTY_PRINT = True
REMOVE_TEMPORARY_FILES = True
MAX_DOWNLOAD_THREADS = 10
MAX_XSLT_THREADS = 4
XSLT_FILE_PATH = os.path.dirname(os.path.realpath(sys.argv[0])) + '/api-format-to-name-value-pairs.xsl'

DEFAULT_OUTPUT_DIRECTORY = os.path.dirname(os.path.realpath(sys.argv[0]))
DEFAULT_USERNAME = ''
DEFAULT_PASSWORD = ''
DEFAULT_DOMAIN = ''
DEFAULT_PROJECT = ''

HOST = ''
URL_HOST = 'http://' + HOST
REST_PREFIX = URL_HOST + '/qcbin/rest/domains'
PROJECTS_TEMPLATE = REST_PREFIX + '/{}/projects'
PROJECT_DEFECTS_TEMPLATE = REST_PREFIX + '/{}/projects/{}/defects?start-index={}&page-size={}&fields={}'
PROJECT_RELEASES_TEMPLATE = REST_PREFIX + '/{}/projects/{}/releases?start-index={}&page-size={}&fields=id,name'
PROJECT_RELEASE_CYCLES_TEMPLATE = \
    REST_PREFIX + '/{}/projects/{}/release-cycles?start-index={}&page-size={}&fields=id,name'

DEFECT_FIELDS = [
    'project',
    'creation-time',
    'priority',
    'target-rel',
    'dev-comments',
    'name',
    'detected-in-rel',
    'closing-date',
    'status',
    'last-modified',
    'severity',
    'id',
    'user-template-13',
    'user-template-01',
    'user-template-03',
    'user-02',
    'user-03',
    'user-12',
    'user-16',
    'user-09',
    'user-07',
    'detected-in-rcyc'
]

DEFECT_FIELDS_ARG = ','.join(DEFECT_FIELDS)

DEFAULT_HEADERS = {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
    'Accept-Encoding': 'gzip, deflate',
    'Host': HOST,
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36'
}


class DefectDownloader(threading.Thread):
    def __init__(self, name, defect_queue, username, password, auth):
        threading.Thread.__init__(self, name=name)
        self._defect_queue = defect_queue
        self._username = username
        self._password = password
        self._auth = auth

    def run(self):
        while not self._defect_queue.empty():
            url, path = self._defect_queue.get()
            try:
                print('{} downloading {}, saving to: {}'.format(threading.current_thread().name, url, path))
                with(open(path, 'w+', encoding='utf-8')) as file:
                    try:
                        response = get_xml(url, self._auth)
                        if response.status_code == 200:
                            file.write(response.text)
                        else:
                            print('Warning: server responded with status code: {}. Trying to re-authenticate.'
                                  .format(response.status_code))
                            file.write(get_xml(url, authenticate(self._username, self._password)).text)
                    except:
                        file.write(get_xml(url, authenticate(self._username, self._password)).text)
            except BaseException as exception:
                print('Thread {} encountered an error: {}'.format(threading.current_thread().name, str(exception)),
                      file=sys.stderr)
            self._defect_queue.task_done()
        print('Thread {} finished.'.format(threading.current_thread().name))


class XsltTransformer(threading.Thread):
    xslt = lxml.etree.XSLT(lxml.etree.parse(XSLT_FILE_PATH))

    def __init__(self, name, transform_queue):
        threading.Thread.__init__(self, name=name)
        self._transform_queue = transform_queue

    def run(self):
        while not self._transform_queue.empty():
            to_transform_path, output_path = self._transform_queue.get()
            try:
                with(open(output_path, 'w+', encoding='utf-8')) as file:
                    print('{} is transforming {}'.format(threading.current_thread().name, to_transform_path))
                    dom = lxml.etree.parse(to_transform_path)
                    transformed = XsltTransformer.xslt(dom)
                    file.write(
                        str(lxml.etree.tostring(transformed, pretty_print=PRETTY_PRINT, method=SERIALIZATION_METHOD),
                            encoding='utf-8'))
            except BaseException as exception:
                print('Thread {} encountered an error: {}'.format(threading.current_thread().name, str(exception)),
                      file=sys.stderr)
            self._transform_queue.task_done()
        print('Thread {} finished.'.format(threading.current_thread().name))


def authenticate(username, password):
    response = requests.get(URL_HOST + '/qcbin/api/authentication/sign-in',
                            auth=(username, password))
    if response.status_code == 401:
        print('Authentication error. Are you sure the credentials you provided are correct?')
        sys.exit(1)
    cookie = response.headers['set-cookie']
    all_headers = re.findall('[a-zA-Z_\-]+=[\da-zA-Z_\-\.]+', cookie)
    return {'Cookie': '; '.join(all_headers)}


def post(url, additional_headers):
    return requests.post(url, headers=build_headers(additional_headers))


def get_xml(url, additional_headers):
    headers = {key: value for key, value in additional_headers.items()}
    headers['Accept'] = 'application/xml'
    return requests.get(url, headers=build_headers(headers))


def get(url, additional_headers):
    return requests.get(url, headers=build_headers(additional_headers))


def build_headers(additional_headers):
    header_container = {}
    append_headers(header_container, DEFAULT_HEADERS)
    append_headers(header_container, additional_headers)
    return header_container


def append_headers(append_to, append_from):
    for header_name, header_value in append_from.items():
        append_to[header_name] = header_value


def domains(headers):
    domains_response = get(REST_PREFIX, headers)
    domains = json.loads(domains_response.text)['Domains']
    try:
        return [domains['Domain']['Name']]
    except:
        return [domain['Name'] for domain in domains['Domain']]


def projects(domain, headers):
    projects_response = get(PROJECTS_TEMPLATE.format(domain), headers)
    projects = json.loads(projects_response.text)['Projects']
    try:
        return [projects['Project']['Name']]
    except:
        return [project['Name'] for project in projects['Project']]


def defects(domain, project, headers, page_size=1000):
    if page_size > 2000:
        raise ValueError('Maximum page size is 2000.')

    defects_response = get(PROJECT_DEFECTS_TEMPLATE.format(domain, project, 1, 1, ''), headers)
    defects_response_obj = json.loads(defects_response.text)
    max_count = 0
    try:
        max_count = int(defects_response_obj['TotalResults'])
    except:
        return

    counter = 1
    result = []
    while counter <= max_count:
        result.append(PROJECT_DEFECTS_TEMPLATE.format(domain, project, counter, page_size, DEFECT_FIELDS_ARG))
        counter += page_size
    return result


def mkdir_p(path):
    try:
        os.makedirs(path)
    except OSError as exc:
        if exc.errno == errno.EEXIST and os.path.isdir(path):
            pass
        else:
            raise


def build_queues(authentication_headers, requested_domain, requested_project, output_directory):
    domains_l = domains(authentication_headers)
    defect_queue = queue.Queue()
    xslt_queue = queue.Queue()
    domain_to_projects = {}
    for domain in domains_l:
        if requested_domain and domain != requested_domain:
            continue
        for project in projects(domain, authentication_headers):
            if requested_project and project != requested_project:
                continue
            if domain not in domain_to_projects:
                domain_to_projects[domain] = []
            domain_to_projects[domain].append(project)
            mkdir_p('{}/{}'.format(output_directory, domain))
            counter = 0
            for defect_url in defects(domain, project, authentication_headers):
                out_file_name = '{}/{}/{}_{}_raw.xml'.format(output_directory, domain, project, counter)
                transformed_file_name = '{}/{}/{}_{}_transformed.xml'.format(output_directory, domain, project, counter)
                defect_queue.put((defect_url, out_file_name))
                xslt_queue.put((out_file_name, transformed_file_name))
                counter += 1
    return defect_queue, xslt_queue, domain_to_projects


def start_download(defect_queue, max_threads, username, password, auth):
    print('Starting defect downloader threads.')
    for downloader_id in range(max_threads):
        print('Starting thread: Downloader#{}'.format(downloader_id))
        worker = DefectDownloader('Downloader#{}'.format(downloader_id), defect_queue, username, password, auth)
        worker.start()

    defect_queue.join()
    print('Finished downloading.')


def xslt_transform(xslt_queue, max_threads):
    print('Starting xslt transformation threads.')
    for xslt_thread_index in range(max_threads):
        print('Starting thread: XsltTransformer#{}'.format(xslt_thread_index))
        worker = XsltTransformer('XsltTransformer#{}'.format(xslt_thread_index), xslt_queue)
        worker.start()

    xslt_queue.join()
    print('Finished xslt transformation.')


def merge_files(domain_to_project_dictionary, output_directory):
    print('Merging xml files started.')
    for domain, projects in domain_to_project_dictionary.items():
        for project in projects:
            print('Merging xml files for project: {}, in domain: {}'.format(project, domain))
            xml_files = glob.glob('{}/{}/{}_*_transformed.xml'.format(output_directory, domain, project))
            tree = lxml.etree.Element('Defects')
            for xml_file in xml_files:
                print('Processing: {}'.format(xml_file))
                data = lxml.etree.parse(xml_file).getroot()
                for result in data.iter('Entities'):
                    tree.extend(result)
            result_file_name = '{}/{}/{}_result.xml'.format(output_directory, domain, project)
            print('Writing file: {}'.format(result_file_name))
            with open(result_file_name, 'w+', encoding='utf-8') as result_file:
                result_file.write(str(lxml.etree.tostring(tree, method=SERIALIZATION_METHOD, pretty_print=PRETTY_PRINT),
                                      encoding='utf-8'))
    print('Finished merging files.')


def remove_temporary_files(domain_to_project_dictionary, output_directory):
    print('Removing temporary files.')
    for domain, projects in domain_to_project_dictionary.items():
        for project in projects:
            raw_files = glob.glob('{}/{}/{}_*_raw.xml'.format(output_directory, domain, project))
            transformed_files = glob.glob('{}/{}/{}_*_transformed.xml'.format(output_directory, domain, project))
            for raw_file in raw_files:
                print('Removing: {}'.format(raw_file))
                os.remove(raw_file)
            for transformed_file in transformed_files:
                print('Removing: {}'.format(transformed_file))
                os.remove(transformed_file)
    print('Finished removing temporary files.')


def release_map(domain, project, headers, page_size=1000):
    print('Generating release id to release name map.')
    return entity_id_name_map(PROJECT_RELEASES_TEMPLATE, domain, project, headers, page_size)


def release_cycle_map(domain, project, headers, page_size=1000):
    print('Generating release cycle id to release cycle name map.')
    return entity_id_name_map(PROJECT_RELEASE_CYCLES_TEMPLATE, domain, project, headers, page_size)


def entity_id_name_map(url_template, domain, project, headers, page_size=1000):
    if page_size > 2000:
        raise ValueError('Maximum page size is 2000.')

    response = get(url_template.format(domain, project, 1, 1), headers)
    response_obj = json.loads(response.text)
    max_count = 0
    try:
        max_count = int(response_obj['TotalResults'])
    except:
        return

    counter = 1
    response_texts = []
    while counter <= max_count:
        response_texts.append(
            get_xml(url_template.format(domain, project, counter, page_size), headers).content)
        counter += page_size

    transformed = {}
    for response_text in response_texts:
        transformed_bytes = lxml.etree.tostring(XsltTransformer.xslt(lxml.etree.fromstring(response_text)))
        transformed_string = str(transformed_bytes, encoding='utf-8')
        with io.StringIO(transformed_string) as transformed_file:
            data = lxml.etree.parse(transformed_file).getroot()
            for element in data.iter('Entity'):
                transformed[element.findtext('id', default='-1')] = element.findtext('name', default='Unknown')

    return transformed


def generate_xslt_for_maps(id_name_map_match_pairs):
    print('Generating xslt for id to name maps value transform.')
    xslt_template = """
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="node()|@*">
        <xsl:copy>
            <xsl:apply-templates select="node()|@*"/>
        </xsl:copy>
    </xsl:template>

    {}
</xsl:stylesheet>
    """
    single_record_template = '<xsl:template match="{}/text()[.=\'{}\']">{}</xsl:template>\n'
    result = ''
    for id_name_map, match in id_name_map_match_pairs:
        for entity_id, entity_name in id_name_map.items():
            result += single_record_template.format(match, entity_id, entity_name)

    return xslt_template.format(result)


def normalize_result(authentication_headers, domain, project, output_directory):
    print('Normalizing result.')
    r_map = release_map(domain, project, authentication_headers)
    rc_map = release_cycle_map(domain, project, authentication_headers)
    xslt_normalizer_text = generate_xslt_for_maps([(r_map, 'detected-in-rel'), (rc_map, 'detected-in-rcyc')])
    print('Finished generating xslt for id to name maps.')
    print('Parsing generated xslt.')
    xslt_root = lxml.etree.XML(xslt_normalizer_text)
    print('Successfully parsed generated xslt.')
    print('Transforming result according to id->name generated rules.')
    xslt = lxml.etree.XSLT(xslt_root)
    dom = lxml.etree.parse('{}/{}/{}_result.xml'.format(output_directory, domain, project))
    transformed = xslt(dom)
    print('Finished result transformation.')
    with(open('{}/{}/{}_result.xml'.format(output_directory, domain, project), 'w+', encoding='utf-8')) as file:
        print('Writing normalized file.')
        file.write(str(lxml.etree.tostring(transformed, method=SERIALIZATION_METHOD, pretty_print=PRETTY_PRINT),
                       encoding='utf-8'))
    print('Finished normalizing result.')


def normalize_results(authentication_headers, domain_to_project_dictionary, output):
    for domain, projects in domain_to_project_dictionary.items():
        for project in projects:
            normalize_result(authentication_headers, domain, project, output)


def init_workflow():
    output_directory, username, password, requested_domain, requested_project = ask_user_for_needed_parameters()
    authentication_headers = authenticate(username=username, password=password)

    defect_queue, xslt_queue, domain_to_project_dictionary = build_queues(
        authentication_headers, requested_domain, requested_project, output_directory)

    start_download(defect_queue, MAX_DOWNLOAD_THREADS, username, password, authentication_headers)
    xslt_transform(xslt_queue, MAX_XSLT_THREADS)
    merge_files(domain_to_project_dictionary, output_directory)
    normalize_results(authentication_headers, domain_to_project_dictionary, output_directory)

    if REMOVE_TEMPORARY_FILES:
        remove_temporary_files(domain_to_project_dictionary, output_directory)
    else:
        print('Not removing temporary files.')
    print('Finished.')


def ask(default, name):
    if default:
        print('Default value for {} found. Not asking for it.'.format(name))
        return default
    else:
        name = name.capitalize()
        if name == 'Project' or name == 'Domain':
            return input('{} (empty for all of them):'.format(name))
        elif name == 'Password':
            return getpass.getpass('{}: '.format(name))
        return input('{}: '.format(name.capitalize()))


def ask_user_for_needed_parameters():
    output_directory = ask(DEFAULT_OUTPUT_DIRECTORY, 'output directory')
    username = ask(DEFAULT_USERNAME, 'username')
    password = ask(DEFAULT_PASSWORD, 'password')
    requested_domain = ask(DEFAULT_DOMAIN, 'domain')
    requested_project = ask(DEFAULT_PROJECT, 'project')
    return output_directory, username, password, requested_domain, requested_project


init_workflow()

