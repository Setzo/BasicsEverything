count = 0


def set_count_g(c):
    global count
    count = c


def set_count(c):
    count = c


print(count)
set_count(4)
print(count)
set_count_g(5)
print(count)
