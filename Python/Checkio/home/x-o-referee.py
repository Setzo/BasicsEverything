def line(row):
    if row[0] == '.':
        return False
    return sorted(row) == sorted(row, reverse=True)


def grid_wins(rows):
    for row in rows:
        if line(row):
            return row[0]
    return 'D'


def diagonal_wins(rows):
    diagonal_rows = ['', '']
    for i in range(0, 3):
        j = 2 - i
        diagonal_rows[0] += rows[i][i]
        diagonal_rows[1] += rows[i][j]
    for i in range(0, 2):
        if line(diagonal_rows[i]):
            return diagonal_rows[i][0]
    return 'D'


def checkio(rows):
    result = diagonal_wins(rows)
    if result in 'XO':
        return result
    result = grid_wins(rows)
    return result if result != 'D' else grid_wins(zip(*rows))
