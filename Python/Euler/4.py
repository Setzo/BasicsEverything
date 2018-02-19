def find():
    sum = 0
    for i in range(999)[::-1]:
        for j in range(999)[::-1]:
            product = str(i * j)
            if product[:3] == product[3:][::-1]:
                sum = max(sum, i * j)
    return sum


print(find())
