class EncouragementsController < ApplicationController

  before_action :auth_user
  before_action :authors_not_allowed
  before_action :not_twice

  def new
    @encouragement = Encouragement.new
    render :new
  end

  private

  def auth_user
    @achievement = Achievement.find(params[:achievement_id])

    unless current_user
      redirect_to achievement_path(@achievement), alert: 'You must be logged in.'
      return
    end
  end

  def authors_not_allowed
    if current_user.id == @achievement.user.id
      redirect_to achievement_path(@achievement), alert: 'You can\'t encourage yourself.'
      return
    end
  end

  def not_twice
    if Encouragement.exists?(user: current_user, achievement: @achievement)
      redirect_to achievement_path(@achievement), alert: 'You can\' encourage twice.'
      return
    end
  end
end