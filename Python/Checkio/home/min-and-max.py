def min(*args, **kwargs):
    key = kwargs.get("key", None)
    try:
        x = sorted(list(*args), key=key)[0]
    except BaseException:
        x = sorted(args, key=key)[0]
    return x


def max(*args, **kwargs):
    key = kwargs.get("key", None)
    try:
        x = sorted(list(*args), key=key, reverse=True)[0]
    except BaseException:
        x = sorted(args, key=key, reverse=True)[0]
    return x
