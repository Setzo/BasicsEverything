class Box:
    serial_number = 0

    @staticmethod
    def _get_next_serial():
        Box.serial_number += 1
        return Box.serial_number

    @classmethod
    def create_empty(cls, code):
        return cls(code, None)

    def __init__(self, code, contents):
        self.code = code
        self.contents = contents
        self.serial = Box._get_next_serial()
        self._identifier = '{}{}'.format(self.code, self.serial)

    def __str__(self):
        return "{} : {} : {}".format(self.code, self.contents, self.serial)

    @property
    def identifier(self):
        return self._identifier

    @identifier.setter
    def identifier(self, identifier):
        self._identifier = identifier


b1 = Box('a1', 'sweets')
b2 = Box('a2', 'clothes')
b3 = Box('a3', 'car')
print(b1)
print(b2)
print(b3)
print(Box.serial_number)
print(Box.create_empty("aa"))
print(b1.identifier)
print(b2.identifier)
print(b3.identifier)
b1.identifier = "2222"
print(b1.identifier)
