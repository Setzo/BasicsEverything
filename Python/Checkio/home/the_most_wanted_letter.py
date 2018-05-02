from collections import Counter
import re


def checkio(text):
    letters = re.findall("[a-z]", text.lower())
    count_map = Counter(letters)
    result = []
    max_count = max(count_map.values())
    for key, value in count_map.items():
        if value == max_count:
            result.append(key)

    return sorted(result)[0]
