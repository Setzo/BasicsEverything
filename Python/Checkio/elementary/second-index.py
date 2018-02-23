def second_index(text: str, symbol: str):
    found = False
    index = 0
    for letter in text:
        if letter == symbol:
            if found:
                return index
            found = True
        index += 1
