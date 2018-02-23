def find(limit):
    for c in range(limit // 3, limit):
        for b in range(limit // 6, c):
            for a in range(1, b)[::-1]:
                if a + b < c:
                    break
                if a + b + c == limit and (a * a) + (b * b) == c * c:
                    return [a, b, c]


numbers = find(1000)
product = 1
for i in numbers:
    product *= i

print(product)
