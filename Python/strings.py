string = "Green"
for i in string:
    print(i)
    print(string[0:string.__len__()])
    print(string[: 4])
    print(string[4:])
    print(string[0:2:2])

numbers = "1, 2, 3, 4, 5"
print(numbers[0::3])
print(numbers[0::3] * 4)

needle = "mm"
haystack = "123mm456"
print(needle in haystack)

age = 24
print("a {0} {0} {1} a".format(age, 2))
print("a %d %d %d %s" % (age, age, 2, "a"))

for i in range(1, 21):
    print("%2d %4d %4d" % (i, i ** 2, i ** 3))
    print("{0:2} {1:4} {2:4}".format(i, i ** 2, i ** 3))
    print("{:2} {:4} {:4}".format(i, i ** 2, i ** 3))
