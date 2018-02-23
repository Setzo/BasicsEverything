import re


def checkio(words):
    cnt = 0
    for word in words.split(" "):
        if re.match("[a-zA-Z]+", word):
            cnt += 1
        else:
            cnt = 0
        if cnt == 3:
            return True
    return False
