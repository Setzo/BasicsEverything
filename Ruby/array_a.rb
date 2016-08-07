arr = []
array = [1, 2, 3]

p array

b = Array.new(3)

puts b.to_s

c = Array.new(4, true)

puts c.to_s

d = Array.new(3, 'abc')
d.first.capitalize!

puts d.to_s

e = Array.new(3) { 'abc' }
e.first.capitalize!

puts e.to_s

f = Array.new(3) { Array.new(3) }
puts f.to_s

g = %w(array\ of words)
puts g.to_s

h = %i(array\ of symbols)
puts h.to_s

puts g.size.to_s + ' ' + g.empty?.to_s

i = %i(up down left right)
puts i.to_s
puts i[0]
puts i[3]
puts i[-1]
puts i[-4]

arr = [0, 1, 2, 3, 4, 5]
puts arr[1..3].to_s

arr[2..3] = [:e, :x]
puts arr.to_s

arr << 6
puts arr.to_s

arr = arr + e * 2
puts arr.to_s

arr = arr * ','
puts arr.to_s