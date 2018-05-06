from functools import wraps


def trace(func):
    @wraps(func)
    def wrap(*args, **kwargs):
        print('Calling {}'.format(func))
        return func(*args, **kwargs)

    return wrap


sequence = map(trace(ord), 'the sequence')
print(sequence)

for item in sequence:
    print(item)

s = ['s', 'm', 'l', 'xl']
c = ['red', 'green', 'blue', 'cyan']
t = ['skinny', 'slim', 'regular', 'oversize']


def combine(s, c, t):
    return '{} {} {}'.format(s, c, t)


combined = map(combine, s, c, t)
for item in combined:
    print(item)
