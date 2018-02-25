def completely_empty(val):
    if not val:
        return True

    try:
        iterator = iter(val)
    except TypeError:
        return False

    for element in val:
        if element == 0 or element == None:
            return False
        if element:
            return completely_empty(element)

    return True
