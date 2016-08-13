a = [1, 2, 3].map { |element| element * 10 }
puts a.to_s

b = [1, 2, 3].reduce { |element, secElement| element + secElement }
puts b

c = [0, 1, 2, 3].keep_if { |element| element < 2 }
puts c.to_s

b = [0, 1, 2, 3].select { |n| n.even? }
puts b.to_s

[1, 2, 3, 4, 5].each_cons(2) { |v| p v }
p '232424243'