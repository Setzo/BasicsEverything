require 'rails_helper'

feature 'achievement page' do

  scenario 'achievement public page' do

    achievement = FactoryGirl.create(:achievement, title: 'Hellllllo')
    visit("/achievements/#{achievement.id}")

    expect(page).to have_content('Hellllllo')
  end

  scenario 'render markdown description' do

    achievement = FactoryGirl.create(:achievement, description: '*YEAAAAAAAAAAAAAAAAAAH*')
    visit("/achievements/#{achievement.id}")

    expect(page).to have_css('em', text: 'YEAAAAAAAAAAAAAAAAAAH')
  end

end