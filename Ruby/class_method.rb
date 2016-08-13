
class Space

  # Static private
  @@privateInfo = 2

  # Not static private
  @info = 4

  def self.show
    puts 'show'
    calc
  end

  def self.privateInfo
    @@privateInfo
  end

  private

  def self.calc
    puts 'calc'
  end

  # private :calc

end

Space.show
puts Space.privateInfo