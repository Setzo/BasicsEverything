import re


def checkio(text, word):
    regex = re.compile('[^\n\S]')
    text = regex.sub('', text).lower()
    lines = text.split("\n")
    first_character_to_match = word[0]
    for i, line in enumerate(lines):
        for j, character in enumerate(line):
            if character == first_character_to_match:
                result = find_word(i, j, word, lines)
                if result:
                    return result
    return None


def find_word(i, j, word, lines):
    for k, character in enumerate(lines[i][j:]):
        if len(word) <= k:
            break
        if word[k] == character:
            if len(word) - 1 == k:
                return [i + 1, j + 1, i + 1, j + k + 1]
    for k, line in enumerate(lines[i:]):
        if len(line) <= j or len(word) <= k:
            break
        if line[j] == word[k]:
            if len(word) - 1 == k:
                return [i + 1, j + 1, i + k + 1, j + 1]
