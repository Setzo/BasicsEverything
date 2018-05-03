def volume(length, *lengths):
    v = length
    for item in lengths:
        v *= item
    return v


print(volume(2, 2))
print(volume(2))
print(volume(2, 4, 2))
print(volume(1, 2, 3, 4, 5))

print('*' * 40)

dimensions = (1, 2, 3, 4, 5)
print(volume(*dimensions))
print(volume(2, *dimensions))
print(volume(2, 3, 4, *dimensions))


def tag(name, **kwargs):
    result = '<' + name
    for key, value in kwargs.items():
        result += ' {}="{}"'.format(str(key), str(value))
    result += '>'
    return result


print(tag('div', id='mike', a=2))
print(tag('body'))


# Will not work --> *args, **kwargs only in this order
# def invalid(**kwargs, *args):
#     pass


def printer(a, b, *cs, d, e):
    print(a)
    print(b)
    print(cs)
    print(d)
    print(e)


# Won't work
# printer(1, 2, 3, 4, 5, 6, 7, 8, 9)
printer(1, 2, 3, 4, 5, 6, 7, d=8, e=9)


# Valid function definition. d and e are required keyword arguments.
def valid(a, b, *args, d, e, **kwargs):
    pass


print('*' * 40)


def color(red, green, blue, yellow='a', **kwargs):
    print(red)
    print(green)
    print(blue)
    print(yellow)
    print(kwargs)


k = {'red': 1, 'green': 2, 'blue': 4, 'a': 3}
color(**k)

k = {'red': 1, 'green': 2, 'a': 3}
color(blue=33, **k)

a = [111, 222]
k = {'yellow': 'yellow', 'a': 3}
color(*a, blue=33, **k)

k = {'red': 1, 'green': 2, 'a': 3}
color(blue=33, **k, mike=None)

print('*' * 40)


def trace(f, *args, **kwargs):
    print(args)
    print(kwargs)
    result = f(*args, **kwargs)
    print(result)


print(int('ff', base=16))
trace(int, 'ff', base=16)
