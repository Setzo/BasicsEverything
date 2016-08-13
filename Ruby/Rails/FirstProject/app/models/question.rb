class Question < ApplicationRecord

  def gravatar
    "http://gravatar.com/avatar/#{Digest::MD5.hexdigest(email)}"
  end
end
