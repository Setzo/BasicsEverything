from bisect import bisect_left
from itertools import chain
from collections.abc import Set


class SortedSet(Set):

    def __init__(self, collection=None):
        self._collection = sorted(set(collection)) if collection is not None else []

    def __contains__(self, item):
        try:
            self.index(item)
            return True
        except ValueError:
            return False

    def __len__(self):
        return len(self._collection)

    def __iter__(self):
        return iter(self._collection)

    def __getitem__(self, item):
        if isinstance(item, slice):
            return SortedSet(self._collection[item])
        return self._collection[item]

    def __repr__(self) -> str:
        result = 'SortedSet('
        result += ', '.join(['{}' for _ in self._collection]).format(*self._collection)
        result += ')'
        return result

    def __eq__(self, rhs):
        if not isinstance(rhs, SortedSet):
            return NotImplemented
        return self._collection == rhs._collection

    def __ne__(self, rhs):
        if not isinstance(rhs, SortedSet):
            return NotImplemented
        return self._collection != rhs._collection

    def __reversed__(self):
        return reversed(self._collection)

    def __add__(self, other):
        return SortedSet(chain(self._collection, other._collection))

    def __mul__(self, rhs):
        return self if rhs > 0 else SortedSet()

    def __rmul__(self, rhs):
        return self if rhs > 0 else SortedSet()

    def index(self, item):
        index = bisect_left(self._collection, item)
        if index != len(self._collection) and self._collection[index] == item:
            return index
        raise ValueError('{} not found.'.format(item))

    def count(self, item):
        return int(item in self)

    def issubset(self, iterable):
        return self <= SortedSet(iterable)

    def issuperset(self, iterable):
        return self >= SortedSet(iterable)

    def intersection(self, iterable):
        return self & SortedSet(iterable)

    def union(self, iterable):
        return self | SortedSet(iterable)

    def symmetric_difference(self, iterable):
        return self ^ SortedSet(iterable)

    def difference(self, iterable):
        return self - SortedSet(iterable)
