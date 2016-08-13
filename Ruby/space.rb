
class Spaceship

  def initialize(name, model)
    @name = name
    @model = model
    @hp = 1500
  end

end

ship = Spaceship.new('Mike', 4)
p ship