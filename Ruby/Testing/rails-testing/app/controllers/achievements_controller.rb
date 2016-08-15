class AchievementsController < ApplicationController

  before_action :authenticate_user!, only: [:new, :create, :edit, :update, :destroy]
  before_action :owners_only, only: [:update, :edit, :destroy]

  def index
    @achievements = Achievement.public_access
  end

  def new
    @achievement = Achievement.new
  end

  def edit
  end

  def update
    if @achievement.update_attributes(achievements_params)
      redirect_to(achievement_path(@achievement))
    else
      render :edit
    end
  end

  def destroy
    @achievement.destroy
    redirect_to achievement_path
  end

  def create
    @achievement = Achievement.new(achievements_params)
    @achievement.user = current_user
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

  def owners_only
    @achievement = Achievement.find(params[:id])

    if current_user != @achievement.user
      redirect_to achievement_path
    end
  end

end