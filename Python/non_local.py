x = 2


def func():
    x = 3

    def local_func():
        nonlocal x
        x = 4

    local_func()
    print(x)

    def local_func_2():
        global x
        x = 44

    local_func_2()
    print(x)


print(x)
func()
print(x)
