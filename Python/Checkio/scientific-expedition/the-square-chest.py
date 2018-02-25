def checkio(lines):
    uniq = unique_lines(lines)
    result = 0
    for i in range(1, 17):
        for j in range(1, ((4 - (i % 4)) % 4) + 1):
            is_square = True
            for x in range(j):
                if (i + x, i + (x + 1)) not in uniq \
                        or ((i + j) + x * 4, (i + j) + (x + 1) * 4) not in uniq \
                        or ((i + j * 4) + x, (i + j * 4) + (x + 1)) not in uniq \
                        or (i + x * 4, i + (x + 1) * 4) not in uniq:
                    is_square = False
            if is_square:
                result += 1
    return result


def unique_lines(lines):
    result = set()
    for line in lines:
        result.add((sorted(line)[0], sorted(line)[1]))
    return result
