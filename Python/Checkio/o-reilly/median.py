def checkio(data):
    data.sort()
    return data[len(data) // 2] if len(data) % 2 != 0 else (data[len(data) // 2] + data[len(data) // 2 - 1]) / 2.0
