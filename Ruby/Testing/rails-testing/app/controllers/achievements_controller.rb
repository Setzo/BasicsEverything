class AchievementsController < ApplicationController

  def index
    @achievements = Achievement.public_access
  end

  def new
    @achievement = Achievement.new
  end

  def edit
    @achievement = Achievement.find(params[:id])
  end

  def create
    @achievement = Achievement.new(achievements_params)
    if @achievement.save
      redirect_to achievement_url(@achievement), notice: 'Achievement has been created.'
    else
      render :new
    end
  end

  def show
    @achievement = Achievement.find(params[:id])
  end

  private

  def achievements_params
    params.require(:achievement).permit(:title, :description, :privacy, :featured, :cover_image)
  end

end