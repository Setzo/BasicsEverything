from functools import reduce

a = list(range(20))
print(a)

filtered = filter(lambda x: x % 2 == 0, a)

x = list(filtered)
print(x)

reduced = reduce(lambda m, n: m + n, x)
print(reduced)
