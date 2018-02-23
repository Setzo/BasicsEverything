def checkio(str_number, radix):
    try:
        return int(str_number, radix)
    except BaseException:
        return -1
