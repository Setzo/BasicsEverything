import unittest
from collections.abc import Set

from collection_protocols import SortedSet


class TestInitialization(unittest.TestCase):

    def test_empty_i(self):
        SortedSet()

    def test_empty(self):
        SortedSet([])

    def test_from_list(self):
        SortedSet([1, 2, 3, 4])

    def test_with_duplicates(self):
        SortedSet([1, 1, 1, 1, 2])

    def test_from_iterable(self):
        def generator_t():
            yield 1
            yield 2
            yield 3
            yield 4

        SortedSet(generator_t())


class TestContainerProtocol(unittest.TestCase):

    def setUp(self):
        self.sorted_set = SortedSet([6, 7, 8, 9])

    def test_in(self):
        self.assertTrue(7 in self.sorted_set)

    def test_nin(self):
        self.assertFalse(10 in self.sorted_set)

    def test_not_in(self):
        self.assertTrue(11 not in self.sorted_set)

    def test_not_in_f(self):
        self.assertFalse(7 not in self.sorted_set)


class TestSizedProtocol(unittest.TestCase):

    def test_empty(self):
        sorted_set = SortedSet()
        self.assertTrue(len(sorted_set) == 0)

    def test_one(self):
        sorted_set = SortedSet([1])
        self.assertTrue(len(sorted_set) == 1)

    def test_n(self):
        sorted_set = SortedSet(range(20))
        self.assertTrue(len(sorted_set) == 20)

    def test_duplicates(self):
        sorted_set = SortedSet([1, 1, 1, 2, 3, 2, 3, 4])
        self.assertTrue(len(sorted_set) == 4)


class TestIterableProtocol(unittest.TestCase):
    def setUp(self):
        self.sorted_set = SortedSet([7, 1, 6, 2, 6])

    def test_iter(self):
        i = iter(self.sorted_set)

        self.assertEqual(next(i), 1)
        self.assertEqual(next(i), 2)
        self.assertEqual(next(i), 6)
        self.assertEqual(next(i), 7)
        self.assertRaises(StopIteration, lambda: next(i))

    def test_for_loop(self):
        index = 0
        expected = [1, 2, 6, 7]
        for item in self.sorted_set:
            self.assertEqual(item, expected[index])
            index += 1


class TestSequenceProtocol(unittest.TestCase):
    def setUp(self):
        self.sorted_set = SortedSet([1, 2, 2, 4, 10, 11, 3])

    def test_index_zero(self):
        self.assertEqual(self.sorted_set[0], 1)

    def test_index_two(self):
        self.assertEqual(self.sorted_set[2], 3)

    def test_last_index(self):
        self.assertEqual(self.sorted_set[-1], 11)

    def test_index_error(self):
        with self.assertRaises(IndexError):
            self.sorted_set[66]

    def test_index_error_negative(self):
        with self.assertRaises(IndexError):
            self.sorted_set[-66]

    def test_slice_from_start(self):
        self.assertEqual(self.sorted_set[:3], SortedSet([1, 2, 3]))

    def test_slice_from_end(self):
        self.assertEqual(self.sorted_set[3:], SortedSet([4, 10, 11]))

    def test_slice_middle(self):
        self.assertEqual(self.sorted_set[2:3], SortedSet([3]))

    def test_slice_middle_2(self):
        self.assertEqual(self.sorted_set[88:], SortedSet())

    def test_slice(self):
        self.assertEqual(self.sorted_set[:], SortedSet([1, 2, 3, 4, 10, 11]))

    def test_reversed(self):
        sorted_set = SortedSet([1, 2, 3, 7, 8])
        reversed_sorted_set = reversed(sorted_set)
        self.assertEqual(next(reversed_sorted_set), 8)
        self.assertEqual(next(reversed_sorted_set), 7)
        self.assertEqual(next(reversed_sorted_set), 3)
        self.assertEqual(next(reversed_sorted_set), 2)
        self.assertEqual(next(reversed_sorted_set), 1)
        with self.assertRaises(StopIteration):
            next(reversed_sorted_set)

    def test_index_positive(self):
        sorted_set = SortedSet([1, 2, 3, 4])
        self.assertEqual(sorted_set.index(3), 2)

    def test_index_negative(self):
        sorted_set = SortedSet([1, 2, 3, 4])
        with(self.assertRaises(ValueError)):
            sorted_set.index(5)

    def test_count_empty(self):
        sorted_set = SortedSet([1, 2, 3, 4])
        self.assertEqual(sorted_set.count(1), 1)

    def test_count_not_empty(self):
        sorted_set = SortedSet([1, 2, 3, 4])
        self.assertEqual(sorted_set.count(6), 0)

    def test_concatenation(self):
        sorted_set = SortedSet([1, 2, 3, 4])
        sorted_set2 = SortedSet([5, 6])
        self.assertEqual(sorted_set + sorted_set2, SortedSet([1, 2, 3, 4, 5, 6]))

    def test_concatenation_equal(self):
        sorted_set = SortedSet([1, 2, 3, 4])
        sorted_set2 = SortedSet([1, 2, 3, 4])
        self.assertEqual(sorted_set + sorted_set2, SortedSet([1, 2, 3, 4]))

    def test_concatenation_duplicates(self):
        sorted_set = SortedSet([1, 2, 3, 4])
        sorted_set2 = SortedSet([1, 2, 5, 6])
        self.assertEqual(sorted_set + sorted_set2, SortedSet([1, 2, 3, 4, 5, 6]))

    def test_repetition_zero(self):
        sorted_set = SortedSet([1, 2, 3])
        self.assertEqual(sorted_set * 0, SortedSet())

    def test_repetition_non_zero(self):
        sorted_set = SortedSet([1, 2, 3])
        self.assertEqual(sorted_set * 10, SortedSet([1, 2, 3]))

    def test_reverse_repetition_zero(self):
        sorted_set = SortedSet([1, 2, 3])
        self.assertEqual(0 * sorted_set, SortedSet())

    def test_reverse_repetition_non_zero(self):
        sorted_set = SortedSet([1, 2, 3])
        self.assertEqual(10 * sorted_set, SortedSet([1, 2, 3]))


class TestEqualityProtocol(unittest.TestCase):

    def test_equal_empty(self):
        sorted_set = SortedSet()
        self.assertEqual(sorted_set, SortedSet())

    def test_equal_one_element(self):
        sorted_set = SortedSet([1])
        self.assertEqual(sorted_set, SortedSet([1]))

    def test_equal_multiple_elements(self):
        sorted_set = SortedSet([1, 2, 3, 4])
        self.assertEqual(sorted_set, SortedSet([4, 3, 2, 1]))

    def test_equal_duplicates(self):
        sorted_set = SortedSet([1, 2, 1, 2, 3, 1])
        self.assertEqual(sorted_set, SortedSet([3, 2, 2, 1, 3]))


class TestRelationalSetProtocol(unittest.TestCase):

    def test_lt_positive(self):
        s = SortedSet({1, 2})
        t = SortedSet({1, 2, 3})
        self.assertTrue(s < t)

    def test_lt_negative(self):
        s = SortedSet({1, 2, 3})
        t = SortedSet({1, 2, 3})
        self.assertFalse(s < t)

    def test_le_lt_positive(self):
        s = SortedSet({1, 2})
        t = SortedSet({1, 2, 3})
        self.assertTrue(s <= t)

    def test_le_eq_positive(self):
        s = SortedSet({1, 2, 3})
        t = SortedSet({1, 2, 3})
        self.assertTrue(s <= t)

    def test_le_negative(self):
        s = SortedSet({1, 2, 3})
        t = SortedSet({1, 2})
        self.assertFalse(s <= t)

    def test_gt_positive(self):
        s = SortedSet({1, 2, 3})
        t = SortedSet({1, 2})
        self.assertTrue(s > t)

    def test_gt_negative(self):
        s = SortedSet({1, 2})
        t = SortedSet({1, 2, 3})
        self.assertFalse(s > t)

    def test_ge_gt_positive(self):
        s = SortedSet({1, 2, 3})
        t = SortedSet({1, 2})
        self.assertTrue(s > t)

    def test_ge_eq_positive(self):
        s = SortedSet({1, 2, 3})
        t = SortedSet({1, 2, 3})
        self.assertTrue(s >= t)

    def test_ge_negative(self):
        s = SortedSet({1, 2})
        t = SortedSet({1, 2, 3})
        self.assertFalse(s >= t)


class TestSetRelationalMethods(unittest.TestCase):

    def test_issubset_proper_positive(self):
        s = SortedSet({1, 2})
        t = [1, 2, 3]
        self.assertTrue(s.issubset(t))

    def test_issubset_positive(self):
        s = SortedSet({1, 2, 3})
        t = [1, 2, 3]
        self.assertTrue(s.issubset(t))

    def test_issubset_negative(self):
        s = SortedSet({1, 2, 3})
        t = [1, 2]
        self.assertFalse(s.issubset(t))

    def test_issuperset_proper_positive(self):
        s = SortedSet({1, 2, 3})
        t = [1, 2]
        self.assertTrue(s.issuperset(t))

    def test_issuperset_positive(self):
        s = SortedSet({1, 2, 3})
        t = [1, 2, 3]
        self.assertTrue(s.issuperset(t))

    def test_issuperset_negative(self):
        s = SortedSet({1, 2})
        t = [1, 2, 3]
        self.assertFalse(s.issuperset(t))


class TestOperationsSetProtocol(unittest.TestCase):

    def test_intersection(self):
        s = SortedSet({1, 2, 3})
        t = SortedSet({2, 3, 4})
        self.assertEqual(s & t, SortedSet({2, 3}))

    def test_union(self):
        s = SortedSet({1, 2, 3})
        t = SortedSet({2, 3, 4})
        self.assertEqual(s | t, SortedSet({1, 2, 3, 4}))

    def test_symmetric_difference(self):
        s = SortedSet({1, 2, 3})
        t = SortedSet({2, 3, 4})
        self.assertEqual(s ^ t, SortedSet({1, 4}))

    def test_difference(self):
        s = SortedSet({1, 2, 3})
        t = SortedSet({2, 3, 4})
        self.assertEqual(s - t, SortedSet({1}))


class TestSetOperationsMethods(unittest.TestCase):

    def test_intersection(self):
        s = SortedSet({1, 2, 3})
        t = [2, 3, 4]
        self.assertEqual(s.intersection(t), SortedSet({2, 3}))

    def test_union(self):
        s = SortedSet({1, 2, 3})
        t = [2, 3, 4]
        self.assertEqual(s.union(t), SortedSet({1, 2, 3, 4}))

    def test_symmetric_difference(self):
        s = SortedSet({1, 2, 3})
        t = [2, 3, 4]
        self.assertEqual(s.symmetric_difference(t), SortedSet({1, 4}))

    def test_difference(self):
        s = SortedSet({1, 2, 3})
        t = [2, 3, 4]
        self.assertEqual(s.difference(t), SortedSet({1}))

    def test_isdisjoint_positive(self):
        s = SortedSet({1, 2, 3})
        t = [4, 5, 6]
        self.assertTrue(s.isdisjoint(t))

    def test_isdisjoint_negative(self):
        s = SortedSet({1, 2, 3})
        t = [3, 4, 5]
        self.assertFalse(s.isdisjoint(t))


class TestInequalityProtocol(unittest.TestCase):

    def test_not_equal_empty(self):
        sorted_set = SortedSet()
        self.assertNotEqual(sorted_set, SortedSet([1]))

    def test_not_equal_one_element(self):
        sorted_set = SortedSet([1])
        self.assertNotEqual(sorted_set, SortedSet([2]))

    def test_not_equal_multiple_elements(self):
        sorted_set = SortedSet([1, 2, 3, 4])
        self.assertNotEqual(sorted_set, SortedSet([66, 3, 2, 1]))

    def test_not_equal_duplicates(self):
        sorted_set = SortedSet([1, 2, 1, 2, 3, 66])
        self.assertNotEqual(sorted_set, SortedSet([3, 2, 2, 1, 3]))


class TestReprProtocol(unittest.TestCase):
    def test_repr(self):
        s = SortedSet([1, 2, 3])
        self.assertEqual('{!r}'.format(s), 'SortedSet(1, 2, 3)')

    def test_empty(self):
        self.assertEqual('{!r}'.format(SortedSet()), 'SortedSet()')


class TestSetProtocol(unittest.TestCase):
    def test_protocol(self):
        self.assertTrue(issubclass(SortedSet, Set))


if __name__ == '__main__':
    unittest.main()
