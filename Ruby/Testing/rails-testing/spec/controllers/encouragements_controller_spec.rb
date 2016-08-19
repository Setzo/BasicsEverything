require 'rails_helper'

RSpec.describe EncouragementsController do

  let(:user) { FactoryGirl.create(:user) }
  let(:author) { FactoryGirl.create(:user) }
  let(:achievement) { FactoryGirl.create(:public_achievement, user: author) }

  describe 'GET new' do

    context 'authenticated user' do

      before { sign_in(user) }

      it 'renders :new template' do
        get :new, achievement_id: achievement.id
        expect(response).to render_template(:new)
      end
      it 'assigns new encouragement to template' do
        get :new, achievement_id: achievement.id
        expect(assigns(:encouragement)).to be_a_new(Encouragement)
      end
    end

    context 'guest user' do
      it 'is redirected back to achievement page' do
        get :new, achievement_id: achievement.id
        expect(response).to redirect_to(achievement_path(achievement))
      end
      it 'assigns flash message' do
        get :new, achievement_id: achievement.id
        expect(flash[:alert]).to eq('You must be logged in.')
      end
    end

    context 'achievement author' do

      before { sign_in(author) }

      it 'is redirected back to achievement page' do
        get :new, achievement_id: achievement.id
        expect(response).to redirect_to(achievement_path(achievement))
      end
      it 'assigns flash message' do
        get :new, achievement_id: achievement.id
        expect(flash[:alert]).to eq('You can\'t encourage yourself.')
      end
    end

    context 'user who already left encouragement for this achievement' do

      before do
        sign_in(user)
        FactoryGirl.create(:encouragement, user: user, achievement: achievement)
      end

      it 'is redirected back to achievement page' do
        get :new, achievement_id: achievement.id
        expect(response).to redirect_to(achievement_path(achievement))
      end
      it 'assigns flash message' do
        get :new, achievement_id: achievement.id
        expect(flash[:alert]).to eq('You can\' encourage twice.')
      end
    end
  end

end