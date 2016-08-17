class Achievement < ApplicationRecord

  belongs_to :user

  validates :title, presence: true
  # validates :title, uniqueness: true
  # validate :unique_title_for_one_user
  validates :user, presence: true
  validates :title, uniqueness: {
      scope: :user_id,
      message: 'You can\'t have two same name achievements.'
  }

  enum privacy: [ :public_access, :private_access, :friends_access ]

  def description_html
    Redcarpet::Markdown.new(Redcarpet::Render::HTML ).render(description)
  end

  def silly_title
    "#{title} by #{user.email}"
  end

  def self.by_letter(letter)
    includes(:user).where('title LIKE ?', "#{letter}%").order('users.email')
  end

  private

  # def unique_title_for_one_user
  #   existing_achievement = Achievement.find_by(title: title)
  #   if existing_achievement && existing_achievement.user == user
  #     errors.add(:title, 'you can\t have two achievements with the same title')
  #   end
  # end

  def self.get_public_achievements
    self.public_access
  end
end
