
require 'minitest/autorun'

class Calculator

  attr_reader :numbers

  def initialize(number_string)
    @numbers = parse_numbers number_string
  end

  def sum
    numbers.inject(:+)
  end

  def mul
    numbers.inject(:*)
  end

  private

  def parse_numbers(number_string)
    number_string.split(',').map { |number| number.to_i }
  end

end

describe Calculator do

  it 'sums provided numbers' do
    calc = Calculator.new('1,2,3,4,5')
    calc.sum.must_equal(15)
  end

  it 'multiplies provided numbers' do
    calc = Calculator.new('1,2,3,4')
    calc.mul.must_equal(24)
  end

end