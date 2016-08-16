require 'rails_helper'

RSpec.describe Achievement, type: :model do

  describe 'validations' do
    it 'requires title' do
      achievement = Achievement.new(title: '')
      achievement.valid?
      expect(achievement.errors[:title]).to include('can\'t be blank')
      expect(achievement.errors[:title]).not_to be_empty
      expect(achievement.valid?).to be_falsy
    end

    it 'requires title to be unique for one user' do
      user = FactoryGirl.create(:user)
      first_achievement = FactoryGirl.create(:public_achievement, title: 'first', user: user)
      second_achievement = Achievement.new(title: 'first', user: user)
      expect(second_achievement.valid?).to be_falsy
    end

    it 'allows different users to have achievements with identical titles' do
      user1 = FactoryGirl.create(:user)
      user2 = FactoryGirl.create(:user)

      first_achievement = FactoryGirl.create(:public_achievement, title: 'first', user: user1)
      second_achievement = Achievement.new(title: 'first', user: user2)

      expect(second_achievement.valid?).to be_truthy
    end
  end

  it 'belongs to user' do
    achievement = Achievement.new(title: 'first', user: nil)
    expect(achievement.valid?).to be_falsy
  end
end