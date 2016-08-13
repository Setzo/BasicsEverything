h = {}

x = {'min' => 0, 'max' => 100}

x0 = {min: :hash, max: 100}

puts x0[:min]

c = Hash.new(0)
puts c[:s]

x0.each { |elem| p elem}
x0.each { |k, v| p k; p v}

