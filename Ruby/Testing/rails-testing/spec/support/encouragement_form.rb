class EncouragementForm
  include Capybara::DSL

  def leave_encouragement(attrs = {})
    puts page.html
    fill_in('encouragements_message', with: attrs.fetch(:text, 'Good job'))
    self
  end

  def submit
    click_on('Encourage')
    self
  end
end