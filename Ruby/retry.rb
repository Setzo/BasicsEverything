
def method

  integer = 2

rescue => e
  attempts ||= 0
  attempts += 1
  if attempts < 3
    puts e.message + '. retrying'
    retry
  else
    puts 'failed'
    raise
  end
end

method rescue false

result = catch :abort do
  throw :abort if 2 == 2
  'ok'
end

puts result.class
puts nil.nil?

str = %q^alpha\^ dsadasdasd^
puts str

message = <<EOS
  This is string.
  possible multiline.
EOS

puts message