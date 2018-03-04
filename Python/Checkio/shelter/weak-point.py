def weak_point(matrix):
    row_sums = [sum(row) for row in matrix]
    colums_sums = [sum(column) for column in zip(*matrix[::-1])]
    return row_sums.index(min(row_sums)), colums_sums.index(min(colums_sums))
