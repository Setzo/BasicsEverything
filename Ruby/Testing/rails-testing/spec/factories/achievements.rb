FactoryGirl.define do
  factory :achievement do
    sequence(:title) { |title_number| "Achievement ##{title_number}"}
    description 'My description'
    privacy :private_access
    featured true
    cover_image '../fixtures/cover_image.png'

    factory :public_achievement do
      privacy :public_access
    end

    factory :private_achievement do
      privacy :private_access
    end
  end
end
