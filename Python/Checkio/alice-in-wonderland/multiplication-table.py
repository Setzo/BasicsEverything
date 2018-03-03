def checkio(first, second):
    matrix = bin_string_to_matrix(binary(first), binary(second))
    reducers = [lambda x, y: x ^ y, lambda x, y: x & y, lambda x, y: x | y]
    return sum(map(lambda reducer: reduce(matrix, reducer), reducers))


def reduce(matrix, reducer):
    result = 0
    for i in range(len(matrix)):
        b_number = []
        for j in range(len(matrix[i])):
            b_number.append(reducer(*matrix[i][j]))
        result += int("".join(str(character) for character in b_number), 2)
    return result


def bin_string_to_matrix(first, second):
    matrix = []
    for i in first:
        matrix.append([])
        for j in second:
            matrix[-1].append((int(i), int(j)))
    return matrix


def binary(value):
    return "{0:b}".format(value)
