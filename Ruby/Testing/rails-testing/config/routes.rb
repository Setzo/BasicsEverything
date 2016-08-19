Rails.application.routes.draw do

  devise_for :users
  resources :achievements do
    resources :encouragements, only: [:new, :create] do
    end
  end

  root to: 'welcome#index'
end
