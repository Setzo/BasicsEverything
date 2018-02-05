tuplee = 1, 2, 3  # tuple

print(tuplee)
print(1, 2, 3)  # tuple
print((1, 2, 3))  # non tuple

# Tuples are immutable
t1 = 1, 2, "qwf", 3,
t2 = (1, 2, "dwq", 4)
t3 = (1, 2, "asdeff", 5)

num1, num2, str1, num3 = t1
print(num1)
print(num2)
print(str1)
print(num3)

print(t1)
print(t1[0])

for i in t1:
    print(i)

a = 2
b = 6
a, b = b, a

print(a)
print(b)
