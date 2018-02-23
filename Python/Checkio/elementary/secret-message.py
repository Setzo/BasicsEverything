import re


def find_message(text):
    return ''.join(re.findall('[A-Z]', text))
