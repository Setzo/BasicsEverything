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
            print(i, j, character)
    return None


def find_word(i, j, word, lines):
    line = lines[i]
    remaining_line_chars = line[j:]
    for k, character in enumerate(remaining_line_chars):
        if len(word) <= k:
            break
        if word[k] == character:
            if len(word) - 1 == k:
                return [i + 1, j + 1, i + 1, j + k + 1]
            continue
    for k, line in enumerate(lines[i:]):
        if len(line) <= j or len(word) <= k:
            break
        if line[j] == word[k]:
            if len(word) - 1 == k:
                return [i + 1, j + 1, i + k + 1, j + 1]
            continue


assert checkio("""DREAMING of apples on a wall,
And dreaming often, dear,
I dreamed that, if I counted all,
-How many would appear?""", "ten") == [2, 14, 2, 16]
assert checkio("""He took his vorpal sword in hand:
Long time the manxome foe he sought--
So rested he by the Tumtum tree,
And stood awhile in thought.
And as in uffish thought he stood,
The Jabberwock, with eyes of flame,
Came whiffling through the tulgey wood,
And burbled as it came!""", "noir") == [4, 16, 7, 16]
