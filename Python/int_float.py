import sys
import decimal
from decimal import Decimal
from fractions import Fraction
import cmath

print(sys.float_info)
print(decimal.getcontext())

x = Decimal(5)
print(x)

print(Decimal(0.8) - Decimal(0.7))
print(Decimal('0.8') - Decimal('0.7'))

print()
print('*' * 40)
print()

two_thirds = Fraction(2, 3)
four_fifths = Fraction(4, 5)
print(two_thirds)
print(four_fifths)
print(Fraction('0.1'))
print(Fraction(0.1))
print(Fraction(2, 3) + Fraction(4, 5))
print(Fraction(2, 3) - Fraction(4, 5))
print(Fraction(2, 3) * Fraction(4, 5))
print(Fraction(2, 3) / Fraction(4, 5))
print(Fraction(2, 3) // Fraction(4, 5))
print(Fraction(2, 3) % Fraction(4, 5))

print()
print('*' * 40)
print()

print(2j)
print(3 + 4j)
print(type(3j))
print(type(3j + 2))
print(2j.imag)
print(2j.real)
print(complex(2, 4))
print(complex(2, 4).imag)
print(complex(2, 4).real)
print(complex(2, 4).conjugate())
# import math
# print(math.sqrt(-1)) # ValueError
print(cmath.sqrt(-1))
print(cmath.phase(1j))
