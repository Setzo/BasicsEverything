from pprint import pprint

x = [(x, y) for x in range(5) for y in range(5)]
pprint(x)

x = [(x, y) for x in range(10) if x > 5 for y in range(10) if x != y]
pprint(x)

x = [[y for y in range(x)] for x in range(10)]
pprint(x)
