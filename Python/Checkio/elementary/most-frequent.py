def most_frequent(data):
    sdata = set(data)
    smax = 0
    scMax = ''
    for occurence in sdata:
        if data.count(occurence) > smax:
            smax = data.count(occurence)
            scMax = occurence
    return scMax
