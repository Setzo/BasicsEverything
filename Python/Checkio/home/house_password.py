import re


def checkio(data):
    return bool(
        len(data) >= 10
        and re.search("[a-z]", data)
        and re.search("[A-Z]", data)
        and re.search("\d", data))
