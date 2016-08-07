a, b = 1, 2

p a
p b

c = 1, 2, 3, 4 # array

p c.to_s

a, b = [1, 2, 3, 4]
p a
p b

a, *b = [1, 2, 3, 4]
p a
p b

a, *b, c = [1, 2, 3, 4]
p a
p b
p c

p ''

first, _, _, _, last = 1, 2, *[3, 4, 5]
p first
p last

first, *, last = 1, 2, *[3, 4, 5]
p first
p last

p ''

r = (1..5)
arr = [9, 99, *r]
puts arr.to_s

h = {a: 'v', b: 'vv'}
puts [*h].to_s
