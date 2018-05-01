import sys

try:
    x = 2 / 0
except:
    # Would not work without this. At least one statement is expected.
    # pass - Noop
    pass

try:
    x = 2 / 0
except ZeroDivisionError as error:
    print('Don\'t divide by zero.', file=sys.stderr)

try:
    try:
        x = 2 / 0
    except ZeroDivisionError:
        # Rethrow
        raise
except ZeroDivisionError as error:
    print(error, file=sys.stderr)

raise ZeroDivisionError('Pls..')
