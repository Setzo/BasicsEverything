def checkio(first, second):
    fs = first.split(',')
    ss = second.split(',')
    return ','.join(sorted([word for word in fs if word in ss]))
