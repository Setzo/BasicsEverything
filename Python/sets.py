test_set = {"something", "something2", "something"}
print(test_set)

x = {}
y = set()

print(x == y)  # x is dictionary, y is set

x = set(range(0, 10))
y = set(range(0, 15, 2))

# Elements presents in both sets.
print(x & y)
print(x.intersection(y))
print(y & x)
print(y.intersection(x))

print('*' * 40)

# Elements present in x, but not y
print(x.difference(y))
print(x - y)

print('*' * 40)

# Elements present in y, but not x
print(y.difference(x))
print(y - x)

print('*' * 40)

# x and y difference
print(x.symmetric_difference(y))
print(y.symmetric_difference(x))
print(x ^ y)
print(y ^ x)

print(x)
x.discard(0)
x.discard(23142)  # no error, does not exist
x.remove(1)
# x.remove(124234)  # error, does not exist
print(x)

if {2, 3, 4}.issubset(x):
    print('subset')

if x.issuperset({2, 3}):
    print('superset')

f = frozenset(range(0, 10))
print(f)
# f.add(3)  # Error
# f.remove(1)  # Error
