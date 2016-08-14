class AchievementsController < ApplicationController

  def new
    @achievement = Achievement.new
  end

  def create
    @achievement = Achievement.new(achievements_params)
    if @achievement.save
      redirect_to root_url, notice: 'Achievement has been created.'
    else
      render :new
    end
  end

  def show
    @achievement = Achievement.find(params[:id])
    @description = Redcarpet::Markdown.new(Redcarpet::Render::HTML ).render(@achievement.description)
  end

  private

  def achievements_params
    params.require(:achievement).permit(:title, :description, :privacy, :featured, :cover_image)
  end

end