def checkio(matrix):
    n = len(matrix)
    if n < 4:
        return False

    for i, column in enumerate(matrix):
        for j in range(n):
            if j + 4 <= n and len(set(column[j: j + 4])) == 1:
                return True

    for i, column in enumerate(zip(*matrix[::-1])):
        for j in range(n):
            if j + 4 <= n and len(set(column[j: j + 4])) == 1:
                return True

    for i in range(3, n):
        for j in range(3, n):
            if len(set([matrix[i - y][j - y] for y in range(4)])) == 1:
                return True

    for i in range(0, n - 3):
        for j in range(3, n):
            if len(set([matrix[i + y][j - y] for y in range(4)])) == 1:
                return True

    return False
