def fib(limit):
    a, b, fib_sum = [0, 1, 0]
    while b < limit: fib_sum, a, b = [fib_sum if b % 2 else fib_sum + b, b, a + b]

    return fib_sum


print(fib(4000000))
