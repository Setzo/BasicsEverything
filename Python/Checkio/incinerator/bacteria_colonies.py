def healthy(grid):
    healthy_cells = []
    x_len = len(grid)
    y_len = len(grid)
    max_size = 0

    for x, row in enumerate(grid):
        for y, value in enumerate(row):
            for size in range(1, min(x_len, y_len)):
                try:
                    is_cell_healthy = is_healthy(x, y, size, grid)
                    is_next_empty = is_empty(x, y, size + 1, grid)
                    if is_cell_healthy and not is_next_empty:
                        continue
                    elif is_cell_healthy and is_next_empty:
                        max_size = max(max_size, size)
                        healthy_cells.append([x, y, size])
                    else:
                        break
                except IndexError:
                    break

    if len(healthy_cells) == 0:
        return 0, 0

    cell = sorted(healthy_cells, key=lambda cell: cell[-1], reverse=True)[0]
    return cell[0], cell[1]


def is_healthy(x, y, size, grid):
    for i in range(size):
        if not is_ones(x, y, i, size, grid):
            return False

    return True


def is_empty(x, y, size, grid):
    for i in range(size):
        if not is_zeroes(x, y, i, size, grid, len(grid), len(grid[0])):
            return False
    return True


def is_zeroes(x, y, i, size, grid, x_len, y_len):
    if 0 <= x - i and 0 <= y - size + i < y_len:
        if not grid[x - i][y - size + i] == 0:
            return False
    if 0 <= x + size - i < x_len and 0 <= y - i:
        if not grid[x + size - i][y - i] == 0:
            return False
    if x + i < x_len and 0 <= y + size - i < y_len:
        if not grid[x + i][y + size - i] == 0:
            return False
    if 0 <= x - size + i < x_len and y + i < y_len:
        if not grid[x - size + i][y + i] == 0:
            return False
    return True


def is_ones(x, y, i, size, grid):
    return grid[x - i][y - size + i] == 1 \
           and grid[x + size - i][y - i] == 1 \
           and grid[x + i][y + size - i] == 1 \
           and grid[x - size + i][y + i] == 1
