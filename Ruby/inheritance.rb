
class Probe

  def deploy
    puts 'deployed'
  end

  def sample
    puts 'sample'
  end

end

class MineralProbe < Probe

  def sample
    puts 'mineral sample'
    super
  end

end

class AtmosphericProbe < Probe

  def sample
    puts 'atmosphere sample'
    super
  end

end

probe = AtmosphericProbe.new

probe.deploy
probe.sample

