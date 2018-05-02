def generator():
    print('yielding 1')
    yield 1
    print('yielding 2')
    yield 2
    print('yielding 3')
    yield 3


g_instance = generator()
print(g_instance)

for item in g_instance:
    print(item)

print(type(generator))
print(type(generator()))
