def deb

  return nil unless block_given?
  puts 'running'

  yield 'parameter..'

end

deb do |a|
  puts 'works ' + a
end

def method_with_block_as_a_parameter(&block)

  p block.class
  yield
end

method_with_block_as_a_parameter do
  puts 'block'
end

p = Proc.new { p 'sdadasdas' }

method_with_block_as_a_parameter(&p)

lam = lambda { p 'lambda' }
lam.call

lam = ->(param1, param2) { p param1.to_s + ' ' + param2.to_s }
lam['weee', 'weee2']

names = %w(aaa bbb www)
puts names.to_s
names.map!(&:upcase)
puts names.to_s

class A
  MAX = 100
end

p A::MAX
A::MIN = 10
p A::MIN