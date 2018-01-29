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
