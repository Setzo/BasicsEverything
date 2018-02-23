def bigger_price(limit, data):
    prices = []
    for dictionary in data:
        prices.append(dictionary['price'])
    prices.sort(reverse=True)

    result = []
    for price in prices[:limit]:
        for dictionary in data:
            if dictionary['price'] == price:
                result.append(dictionary)
    return result
