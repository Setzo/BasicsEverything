require 'rails_helper'

feature 'create_new_achievement' do

  scenario 'create new achievement with valid data' do
    visit('/')
    click_on('New Achievement')

    achievement_title = 'Read a book'

    fill_in('Title', with: achievement_title)
    fill_in('Description', with: '2/10 book. ')
    select('Public', from: 'Privacy')
    check('Featured achievement')
    attach_file('Cover image', "#{Rails.root}/spec/fixtures/cover_image.png")

    click_on('Create Achievement')

    expect(page).to have_content('Achievement has been created')
    expect(Achievement.last.title).to eq(achievement_title)
  end

end