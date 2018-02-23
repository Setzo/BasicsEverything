def checkio(number):
    if number % 15 == 0:
        return "Fizz Buzz"
    elif number % 5 == 0:
        return "Buzz"
    return "Fizz" if number % 3 == 0 else str(number)
