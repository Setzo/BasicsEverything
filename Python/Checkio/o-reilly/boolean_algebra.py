OPERATION_NAMES = {
    "conjunction": lambda x, y: x & y,
    "disjunction": lambda x, y: x | y,
    "implication": lambda x, y: 0 if x == 1 and y == 0 else 1,
    "exclusive": lambda x, y: x ^ y,
    "equivalence": lambda x, y: 1 if x == y else 0
}


def boolean(x, y, operation):
    return OPERATION_NAMES[operation](x, y)
