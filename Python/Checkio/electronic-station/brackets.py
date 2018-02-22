def checkio(expression):
    bracket_map = {'{': '}', '[': ']', '(': ')'}
    closing_brackets = bracket_map.values()
    opening_brackets = bracket_map.keys()
    stack = []
    for character in expression:
        if character in opening_brackets:
            stack.append(character)
            continue
        if character not in closing_brackets:
            continue

        opening_bracket = pop_safe(stack)
        if not opening_bracket or not bracket_map[opening_bracket] == character:
            return False
    return len(stack) == 0


def pop_safe(data):
    try:
        return data.pop()
    except BaseException:
        return ''
