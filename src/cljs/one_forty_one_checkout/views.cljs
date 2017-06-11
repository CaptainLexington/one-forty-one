(ns one-forty-one-checkout.views
  (:require [re-frame.core :as re-frame]
            [one-forty-one-checkout.beds :as beds]
            [one-forty-one-checkout.dartboard :refer [dartboard]]))


(defn main-panel []
  (let [lame (re-frame/subscribe [:name])
        game (re-frame/subscribe [:game])
        score (re-frame/subscribe [:score]) ]
    (fn []
      [:div
       [:h2  "You are playing " @game]
       [:p "Current Checkout: " (:current-checkout @score)]
       [:p "Current Value: " (:current-value @score)]
       [:p "On Dart: " (:on-dart @score)]
       [:p "Successes: " (:successes @score)]
       [:p "Total Attempts: " (:total-attempts @score)]
       [dartboard ]])))
