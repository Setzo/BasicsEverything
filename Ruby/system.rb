puts 'using backticks'
res = `uname -r`
puts res

puts 'using system:'
res = system "uname -r"
puts res

puts 'using %x'
res = %x(uname -r)
puts res