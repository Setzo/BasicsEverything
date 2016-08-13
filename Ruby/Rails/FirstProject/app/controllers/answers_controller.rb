class AnswersController < ApplicationController

  def create
    Answer.create(answer_params)

    redirect_to root_path
  end

  private

  def answer_params
    params.require(:answer).permit(:email, :body, :question_id)
  end

end
