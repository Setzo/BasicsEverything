def checkio(number):
    product = 1
    for i in str(number).replace('0', ''):
        product *= int(i)
    return product

