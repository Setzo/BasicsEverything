require 'rails_helper'
require_relative '../support/login_form'
require_relative '../support/achievement_page'
require_relative '../support/encouragement_form'

feature 'create encouragement' do

  let(:login_form) { LoginForm.new }
  let(:achievement_page) { AchievementPage.new }
  let(:encouragement_form) { EncouragementForm.new }

  let(:user) { FactoryGirl.create(:user) }
  let(:achievement) { FactoryGirl.create(:public_achievement, user: user) }

  scenario 'authenticated user leaves encouragement for achievement' do

    login_form.visit_page.login_as(user)

    achievement_page.visit_page(achievement).encourage
    encouragement_form.leave_encouragement(text: 'Nice one').submit

    expect(page).to have_content("Encouragement left by #{user.email}")
    expect(page).to have_content('Nice one')
    expect(page).to have_css('#encouragement_quantity', text: '1')
  end

end