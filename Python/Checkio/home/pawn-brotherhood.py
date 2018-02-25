def safe_pawns(cords):
    matrix = cord_to_matrix(cords)
    safe_pawn_count = 0
    for i in range(0, 8):
        for j in range(0, 8):
            if matrix[i][j] == 1 and j - 1 >= 0:
                if i - 1 >= 0 and matrix[i - 1][j - 1] == 1:
                    safe_pawn_count += 1
                elif i + 1 <= 7 and matrix[i + 1][j - 1] == 1:
                    safe_pawn_count += 1
    return safe_pawn_count


def cord_to_matrix(cords):
    matrix = [[0 for x in range(8)] for y in range(8)]
    for cord in cords:
        matrix[ord(cord[0]) - ord('a')][int(cord[1]) - 1] = 1
    return matrix


if __name__ == '__main__':
    # These "asserts" using only for self-checking and not necessary for auto-testing
    assert safe_pawns({"b4", "d4", "f4", "c3", "e3", "g5", "d2"}) == 6
    assert safe_pawns({"b4", "c4", "d4", "e4", "f4", "g4", "e5"}) == 1
    print("Coding complete? Click 'Check' to review your tests and earn cool rewards!")
