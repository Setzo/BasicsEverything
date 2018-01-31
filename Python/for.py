string = "string"

for letter in string:
    print(letter)

for number in [1, 2, 3, 4, 5]:
    print(number)

for i in range(0, 100, 5):
    print(i)

for i in [1]:
    break
else:
    print("will not be printed, was broken out of")

for i in [1]:
    continue
# Works only if for loop is NOT broken out of
else:
    print("broken out of")
