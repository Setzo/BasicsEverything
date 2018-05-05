class ReprStrTest:

    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __str__(self):
        return "(x={}, y={})".format(self.x, self.y)

    def __repr__(self):
        return "ReprStrTest(x={}, y={})".format(self.x, self.y)

    def __format__(self, format_spec):
        if format_spec == 'ax':
            return 'Formatted {} {} {}'.format(self.x, self.y, format_spec)
        return 'Not ax, other format returned'


rst = ReprStrTest('aaa', 'bbb')
print(str(rst))
print(repr(rst))
print(rst)
print([rst, rst])
print((rst, rst))
print([rst] * 2)
print('{}'.format(rst))
print('{:ax}'.format(rst))
# Force __repr__
print('{!r}'.format(rst))
# Force __str__
print('{!s}'.format(rst))
