
a = 'abc'

b = a

a.upcase!

puts a
puts b

puts a.object_id
puts b.object_id

b = a.clone
b.downcase!

puts a
puts b

puts a.object_id
puts b.object_id
