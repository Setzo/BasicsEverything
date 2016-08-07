
module API

  MAX = 10

  class Test
    def testing
      puts 'test'
    end
  end

  def self.method2
    puts 'mm'
  end

end

p API::MAX

API.method2

test = API::Test.new

test.testing

module NeedsToBeIncluded

  def metssas
    puts 'dasdas'
  end
end

class TestCl
  include NeedsToBeIncluded
end

class TestCl2
  extend NeedsToBeIncluded
end

x = TestCl.new
x.metssas

TestCl2.metssas


