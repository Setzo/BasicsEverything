import turtle

cnt = 15
before = 0
for i in range(0, 1000, 4):
    cnt
    before = cnt
    turtle.forward(cnt)
    cnt = cnt + i + 1
    turtle.right(250)
    turtle.forward(cnt)
    cnt = cnt + i + 2
    turtle.left(250)
    turtle.back(15)
    turtle.left(250)
    turtle.forward(cnt)
    cnt = cnt + i + 3
    turtle.left(110)
    cnt = before + i

turtle.done()
