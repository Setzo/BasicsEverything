from contextlib import contextmanager


# with expression as x:  # x is not the result of expression. x is expression.__enter__()
#     pass


class LoggingContextManager:
    def __enter__(self):
        print('LoggingContextManager.__enter__()')
        return 2

    # If this doesn't return anything, exceptions will be propagated. By returning true
    # we tell Python not to propagate exceptions.
    def __exit__(self, exc_type, exc_val, exc_tb):
        print('LoggingContextManager.__exit__({}, {}, {})'.format(exc_type, exc_val, exc_tb))
        print('LoggingContextManager.__exit__({}, {}, {})'.format(type(exc_type), type(exc_val), type(exc_tb)))
        return True


with LoggingContextManager() as test:
    print(test)

print()
print('-' * 40)
print()

with LoggingContextManager() as test:
    print(test)
    raise IndexError('aa')


@contextmanager
def mcm():
    # enter
    print('mcm.__enter__()')
    try:
        yield 1
        print('mcm.__exit__()')
        # normal exit
    except:
        # exc exit
        print('mcm.__exit__(exc)')
        raise


with mcm() as x, LoggingContextManager() as lcm2:
    print(x)
    print(lcm2)
