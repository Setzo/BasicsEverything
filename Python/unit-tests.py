import unittest


def function_that_does_something():
    pass


class FunctionThatDoesSomethingTest(unittest.TestCase):

    def function_runs_without_errors(self):
        function_that_does_something()


if __name__ == '__main__':
    unittest.main()
