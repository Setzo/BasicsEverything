text_file = open("file", "r")

for line in text_file:
    print(line)

text_file.close()

with open("file", "r") as text_file:
    for line in text_file:
        print(line)

with open("file", "r") as text_file:
    lines = text_file.readlines()

print(lines)
print([line for line in lines])
for line in lines:
    print(line)

for reversed_line in lines[::-1]:
    print(reversed_line[::-1])
