print(range(0, 20))

listR = list(range(20))

print(listR)

even = range(0, 10000, 3)
print(even.index(2100))

toCheck = 35432
eights = range(8, 800000, 8)
print(toCheck in eights)

o100 = range(0, 100)
subRange = o100[40:50:2]
print(subRange)

subRange2 = o100[90:110:3]
print(subRange2)

backward = "rpw"
print(backward[::-1])

for i in range(0, 10)[::-1]:
    print(i)

for i in range(9, -1, -1):
    print(i)
