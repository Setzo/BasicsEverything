
class Spaceship

  # get / set
  # attr_accessor :destination, :name

  # get
  # attr_reader :destination, :name

  # set
  # attr_writer :destination, :name

  def launch(destination)
    @destination = destination
  end

  def cancel_launch
    destination = ''
    self.destination = ''
  end

  def destination
    @destination
  end

end

ship = Spaceship.new

ship.launch('Earth')

puts ship.inspect
p ship

puts ship.destination
