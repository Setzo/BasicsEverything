def checkio(sum_of_numbers):
    numbers = triangular_numbers(sum_of_numbers)
    for i in range(len(numbers)):
        for j in range(len(numbers[:i])):
            if sum(numbers[j:i]) == sum_of_numbers:
                return numbers[j:i]
    return []


def triangular_numbers(limit):
    triangular_number = 1
    counter = 1
    result = []
    while triangular_number <= limit:
        result.append(triangular_number)
        counter += 1
        triangular_number += counter
    return result
