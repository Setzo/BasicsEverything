from pprint import pprint

a = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
b = [6, 7, 8, 9, 0, 4, 5, 8, 1, 9]
c = [5, 4, 3, 2, 1, 5, 5, 5, 5, 5]
d = [a, b, c]

pprint(d)
# Transposed list
pprint(list(zip(*d)))
