class Calc:

    def __init__(self, number):
        self._number = number

    def add(self):
        print('Adding')
        return self._number + 2


c = Calc(2)

# Both are equivalent. The first one calls the second one behind the scenes.
print(c.add())
print(Calc.add(c))


class Parent:
    def x(self):
        print('Parent')


class Child(Parent):
    def a(self):
        print('Child')
        self.x()


a = Child()
a.a()
