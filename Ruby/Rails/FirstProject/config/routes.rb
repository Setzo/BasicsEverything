Rails.application.routes.draw do

  resources :questions
  root 'home#index'

  get '/about' => 'home#about'

  post '/questions' => 'home#temp'
  post '/answers' => 'home#temp'

  get '/question/:id' => 'home#question'

  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
end
