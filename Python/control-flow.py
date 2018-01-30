userInput = int(input("int pls\n"))
userInput2 = int(input("int2 pls\n"))

if userInput % 2 == 0:
    print("even {}".format(userInput))
elif userInput == 3:
    print("3")
else:
    print("nope {}".format(userInput))

if 10 < userInput < 15 and userInput2 < 30:
    print("userInput > 10, < 15, userInput2 < 30")
elif userInput == 3 or userInput2 == 1:
    print("3 or 1")
else:
    print("nope")

if "python" in input("input pls\n"):
    print("python inside input")
