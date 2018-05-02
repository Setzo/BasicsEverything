from collections import Counter


# Or:
# checkio = lambda data: [v for v in data if Counter(data)[v] > 1]

def checkio(data):
    counter = Counter(data)
    return [v for v in data if counter[v] > 1]
