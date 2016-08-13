message = if 2 > 10 then
            "msg1"
          else
            "msg2"
          end

m3s = 2 < 5 ? 2 : 4

puts message
puts m3s

m3s ||= 4
m5s ||= 666

puts m3s
puts m5s

a = 1

a += 2 while a < 5
puts a

a -= 2 until a < 0
puts a

for i in 1..3
  puts i
end

[11, 22, 33].each do |sp|
  puts sp
end

1.upto(2) { |i| puts i }

3.downto(2) { |i| puts i }

3.times { |i| puts i }

1.step(11, 5) { |i| puts i }

begin
  x = 2/0
  puts 'noexc'
rescue
  puts 'exception'
end

def launch

  raise StandardError, 'error'

rescue Exception => e
  puts e.message
  puts 'exc'
rescue StandardError
  puts 's'
  puts $!.message
else
  puts 'no exceptions'
ensure
  puts 'zawsze bedzie wykonane'
end

launch