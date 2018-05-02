from math import acos, degrees


def checkio(c, b, a):
    tc = c
    tb = b
    ta = a
    c = max(c, b, a)
    a = min(c, b, a)
    b = (tc + tb + ta) - (c + a)
    if a + b > c and a + c > b and b + c > a:
        return sorted([angle(b, c, a), angle(c, a, b), angle(a, b, c)])
    return [0, 0, 0]


def angle(a, b, c):
    return round(degrees(acos((a * a + b * b - c * c) / (2.0 * a * b))))
