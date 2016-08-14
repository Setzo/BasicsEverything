require_relative '../lib/playground.rb'

describe Playground do

  context 'when there are no children' do

    # before do
    #   @playground = Playground.new(0)
    # end
    #
    # it 'is quite boring place' do
    #   mood = @playground.mood
    #   expect(mood).to eq('boring')
    # end
    #
    # it 'is empty' do
    #   expect(@playground).to be_empty
    # end

    let(:playground) { Playground.new(0) }

    it 'is quite boring place' do
      mood = playground.mood
      expect(mood).to eq('boring')
    end

    it 'is empty' do
      expect(playground).to be_empty
    end

  end

end