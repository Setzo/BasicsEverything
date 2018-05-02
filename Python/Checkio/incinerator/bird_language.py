import re

VOWELS = "aeiouy "


def translate(phrase):
    result = ''.join([letter for index, letter in enumerate(phrase) if
                      index == 0 or phrase[index - 1] in VOWELS or letter not in VOWELS])
    return re.sub('([aeiouy])\\1\\1', '\\1', result)
