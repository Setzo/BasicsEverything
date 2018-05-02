from itertools import islice, count


def is_odd(x):
    return x % 2 == 1


thousand_primes = islice((x for x in count() if x % 2 == 0), 1000)
print(thousand_primes)

print(any([False, False, True]))
print(all([False, False, True]))

print(any(is_odd(x) for x in range(1000)))
print(all(is_odd(x) for x in range(1000)))

s = [1, 2, 3, 4, 5]
z = [4, 5, 6, 7, 8]
y = [2, 4, 2, 5, 9]

for item in zip(s, z):
    print(item)

for s_item, z_item, y_item in zip(s, z, y):
    print(s_item, z_item, y_item)

for aggregate in zip(s, z, y):
    print("min: {}, max: {}, avg: {}".format(min(aggregate), max(aggregate), sum(aggregate) / len(aggregate)))
