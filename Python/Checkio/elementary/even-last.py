def checkio(array):
    if len(array) == 0:
        return 0
    sum = 0
    for i in range(0, len(array), 2):
        sum += array[i]
    return sum * array[len(array) - 1]
