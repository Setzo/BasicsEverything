squares = (x * x for x in range(1, 1000000))

# Generator
print(squares)
print(type(squares))

a = list(squares)
b = list(squares)

# 999999
print(len(a))
# 0 - Generators are like streams, once all items has been processed, they do not return anything
# or in stream way - are closed.
print(len(b))

# Memory efficient
print(sum(x * x for x in range(1, 1000000)))

# Generator
print(type(x * x for x in range(1)))

print(sum(x * x for x in range(100000) if x % 2 == 0))
