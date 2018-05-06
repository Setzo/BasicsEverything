class TestIterator:
    def __init__(self, data):
        self.index = 0
        self.data = data

    def __iter__(self):
        return self

    def __next__(self):
        try:
            result = self.data[self.index]
        except IndexError:
            raise StopIteration()
        self.index += 1
        return result


class TestIterable:
    def __init__(self, data):
        self.data = data

    def __iter__(self):
        return TestIterator(self.data)


iterator = TestIterable(list(range(20)))
for item in iterator:
    print(item)

print([i * 3 for i in TestIterable(list(range(4)))])
