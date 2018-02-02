listVar = list([1, 2, 3, 4, 5])

listVar.append(5)

for i in listVar:
    print(i)

otherListVar = list([6, 7, 8, 9])

for i in listVar + otherListVar:
    print(i)

print(listVar + otherListVar)
print(otherListVar + listVar)

sumLists = otherListVar + otherListVar
print(sorted(sumLists))
print(sumLists.sort())  # None
print(sumLists)

a = list([5, 4, 1, 2, 7])
b = a
a.sort(reverse=True)
print(a)
print(b)
print(a == b)

