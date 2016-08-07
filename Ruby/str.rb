
puts 'hello'[1,3]

a = 'Wojtek'
a['tek'] = 'ciech'
puts a
puts a * 2

puts '%05d' % 11
puts a.chars
puts a.codepoints
puts a.bytes
puts a.downcase
puts a
puts a.downcase!
puts a

str = '  sadasdas dasas dasdas   '

puts str.strip!.capitalize! + '!'

puts 'ht ht ht'.gsub('ht', 'p').split(' ').join('*')

a = /\d/

puts '5' =~ a ? 'matches': 'does not match'
puts '5' !~ a ? 'does not match': 'matches'

m = /(\d+):(\d+)/.match('time: 12:23 am')
puts m
puts $&
puts m.pre_match
puts $`
puts m.post_match
puts $'
puts m[0]
puts m[1]
puts $1
puts m[2]
puts $2
puts m[3]
puts $3

puts 'time'.scan(/\w/)
puts 'Wojciech Pruszak'.gsub(/(\w+) (\w+)/, '\\2 \\1')

pass = 'Password: gdf345345'
puts pass
puts pass.gsub(/(Password:)\s+(.*)/) { |match| "#{$1} #{'*' * $2.length}"}