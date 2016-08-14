FactoryGirl.define do
  factory :achievement do
    sequence(:title) { |title_number| "Achievement ##{title_number}"}
    description 'My description'
    privacy Achievement.privacies[:private_access]
    featured true
    cover_image '../fixtures/cover_image.png'

    factory :public_achievement do
      privacy Achievement.privacies[:public_access]
    end
  end
end
