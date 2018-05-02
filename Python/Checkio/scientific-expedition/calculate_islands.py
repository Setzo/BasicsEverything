def checkio(land: list) -> list:
    islands = []
    for i, row in enumerate(land):
        for j, column in enumerate(row):
            if column == 1:
                islands.append(island_calculate(land, i, j))
    return sorted(islands)


def island_calculate(land: list, i: int, j: int) -> int:
    result = 0
    if land[i][j] == 1:
        land[i][j] = 0
        result += 1
    for k in range(max(0, i - 1), min(i + 2, len(land))):
        for m in range(max(0, j - 1), min(j + 2, len(land[i]))):
            if land[k][m] == 1:
                result += island_calculate(land, k, m)
    return result
