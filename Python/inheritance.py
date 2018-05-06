class Parent:

    def __init__(self):
        print('Parent.__init__()')

    def func(self):
        print('Parent.func()')


class Parent2:
    def func(self):
        print('Parent2.func()')


class Child(Parent, Parent2):

    def __init__(self):
        super().__init__()
        print('Child.__init__()')

    # def func(self):
    #     print('Child.func()')


x = Child()
x.func()

print(isinstance(x, Child))
print(isinstance(x, Parent))
print(isinstance(x, (Child, Parent)))
# int or Child
print(isinstance(x, (int, Child)))
x.func()

print('*' * 40)

print(issubclass(Parent, Child))
print(issubclass(Child, Parent))  # True
print(issubclass(int, Parent))

print(Child.__bases__)
# Method resolution order
print(Child.__mro__)
print(Child.mro())
