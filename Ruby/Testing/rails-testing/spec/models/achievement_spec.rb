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

    it {should validate_presence_of(:title)}

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

    it {should validate_uniqueness_of(:title).scoped_to(:user_id).with_message('You can\'t have two same name achievements.')}

    it 'belongs to user' do
      achievement = Achievement.new(title: 'first', user: nil)
      expect(achievement.valid?).to be_falsy
    end

    it {should validate_presence_of(:user)}

    it 'has belongs_to user association' do
      user = FactoryGirl.create(:user)
      achievement = FactoryGirl.create(:public_achievement, user: user)
      expect(achievement.user).to eq(user)

      u = Achievement.reflect_on_association(:user)
      expect(u.macro).to eq(:belongs_to)
    end

    it {should belong_to(:user)}
  end

  it 'converts markdown to html' do
    achievement = Achievement.new(description: '**desc**')
    expect(achievement.description_html).to include('<strong>desc</strong>')
  end

  it 'has silly title' do
    achievement = Achievement.new(title: 'new one', user: FactoryGirl.create(:user, email: 'test@test.test'))
    expect(achievement.silly_title).to eq('new one by test@test.test')
  end

  it 'only fetches achievements, which title starts with provided letter' do
    user = FactoryGirl.create(:user)
    first_achievement = FactoryGirl.create(:public_achievement, title: 'First', user: user)
    second_achievement = FactoryGirl.create(:public_achievement, title: 'Second', user: user)

    expect(Achievement.by_letter('F')).to eq([first_achievement])
  end

  it 'sorts achievements by user email' do
    user_aaa = FactoryGirl.create(:user, email: 'aaa@aaa.aaa')
    user_bbb = FactoryGirl.create(:user, email: 'bbb@bbb.bbb')
    first_achievement = FactoryGirl.create(:public_achievement, title: 'Fa', user: user_bbb)
    second_achievement = FactoryGirl.create(:public_achievement, title: 'Fo', user: user_aaa)

    expect(Achievement.by_letter('F')).to eq([second_achievement, first_achievement])
  end

end