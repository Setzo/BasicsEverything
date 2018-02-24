import re


def double_substring(line):
    if not line:
        return 0
    matches = re.findall('(?=(.+).*\\1)', line)
    return 0 if not matches else max([len(match) for match in matches])
