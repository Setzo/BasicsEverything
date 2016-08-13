class Answer < ApplicationRecord

  include HasGravatar

  belongs_to :question

end
