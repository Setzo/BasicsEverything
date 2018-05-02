from itertools import count, islice


def generate_series():
    a = 1
    b = 2
    yield a
    for _ in count(start=1):
        yield b
        a, b = b, a + b


def write_series(limit):
    with open('series.txt', mode='wt', encoding='utf-8') as f:
        f.writelines('{}\n'.format(element) for element in islice(generate_series(), limit))


def read_series(limit):
    with open('series.txt', mode='rt', encoding='utf-8') as f:
        return [int(element) for element in islice(f, limit)]


write_series(1000)
print(read_series(1000))
