import socket


class Resolver:
    def __init__(self):
        self._cache = {}

    def __call__(self, host):
        if host not in self._cache:
            self._cache[host] = socket.gethostbyname(host)
        return self._cache[host]

    def clear(self):
        self._cache.clear()

    def has_host(self, host):
        return host in self._cache


resolver = Resolver()

print(resolver('amazon.com'))

print(resolver._cache)
print(resolver.has_host('amazon.com'))
resolver.clear()
print(resolver.has_host('amazon.com'))


def sequence_class(is_immutable=False):
    return tuple if is_immutable else list


print(sequence_class())
print(sequence_class(True))
print(sequence_class(False))
print(sequence_class(True)([1, 2, 3]))
print(sequence_class(False)([1, 2, 3]))

if callable(2):
    print('2 is callable')
else:
    print('2 is not callable')

if callable(Resolver):
    print('Resolver is callable')
else:
    print('Resolver is not callable')
