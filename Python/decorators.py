import re


class Counter:

    def __init__(self, func):
        self._func = func
        self.__name__ = func.__name__
        self.count = 0

    def __call__(self, *args, **kwargs):
        self.count += 1
        print('Called \'{}\' {} times.'.format(self._func.__name__, self.count))
        return self._func(*args, **kwargs)


def remove_non_letters_decorator(original_func):
    def wrap(*args, **kwargs):
        result = original_func(*args, **kwargs)
        if result is None:
            return
        return re.sub('[^a-zA-Z]', '', result, flags=re.UNICODE)

    wrap.__name__ = original_func.__name__
    return wrap


@Counter
@remove_non_letters_decorator
def word():
    return 'word1 \t\n\r '


@remove_non_letters_decorator
@Counter
def letter():
    return 'lette#@r'


@Counter
@remove_non_letters_decorator
def gibberish():
    return '!!gi123bb!@#e@#rish@##$@32'


print(word())
print(letter())
print(gibberish())
print(word())
print(word())


class InstanceDecorator:
    def __init__(self, log):
        self.log = log

    def __call__(self, func):
        def wrap(*args, **kwargs):
            if self.log:
                print('Logging')
            return func(*args, **kwargs)

        wrap.__name__ = func.__name__
        return wrap


logging_instance_decorator = InstanceDecorator(True)
not_logging_instance_decorator = InstanceDecorator(False)


@logging_instance_decorator
@remove_non_letters_decorator
@Counter
def loggable_func():
    print('log me pls')


@not_logging_instance_decorator
def non_loggable_func():
    print('do not log me')


loggable_func()
non_loggable_func()

import functools


def hello_world_dec(func):
    @functools.wraps(func)
    def wrap(*args, **kwargs):
        print('hello world')
        return func(*args, **kwargs)

    return wrap


def hello_world_metadata_losing_dec(func):
    def wrap(*args, **kwargs):
        print('hello world')
        return func(*args, **kwargs)

    return wrap


@hello_world_dec
def do_something():
    """
    does something
    """
    print('do_something')


@hello_world_metadata_losing_dec
def do_something2():
    """
    does something
    """
    print('do_something')


do_something()
print(do_something.__name__)
print(do_something.__doc__)
print('*' * 40)
do_something2()
print(do_something2.__name__)
print(do_something2.__doc__)
