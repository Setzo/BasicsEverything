import re


def repeat_inside(line):
    matches = re.findall('(?=(([\S]+?)\\2+))', line)
    matches = [match[0] for match in matches]
    return max(matches, key=len) if matches else ''
