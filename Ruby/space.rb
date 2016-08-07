
class Spaceship

  def initialize(name, model)
    @name = name
    @model = model
    @hp = 1500
  end

  def +(other)
    2
  end

end

ship = Spaceship.new('Mike', 4)
ship2 = Spaceship.new('mm', 3)

puts ship + ship2