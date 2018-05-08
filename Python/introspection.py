from pprint import pprint
import inspect
import collection_protocols

print(type(int))
print(int.__class__)
print(int.__class__.__class__.__class__.__class__.__class__)

pprint(print(dir(int)))
pprint(print(globals()))


def local_scope_func():
    loc = 2
    print(locals())


def local_x(a, b, c):
    print('{b} {c} {a}'.format(**locals()))


local_scope_func()
local_x(1, 2, 3)

print()
print(inspect.ismodule(collection_protocols))
print(inspect.getmembers(collection_protocols, inspect.isclass))
