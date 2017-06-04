(ns one-forty-one-checkout.events
    (:require [re-frame.core :as re-frame]
              [one-forty-one-checkout.db :as db]
              [one-forty-one-checkout.games.checkout-practice :refer [score]]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
  :score
  (fn [db [_ bed-id]]
  (assoc db
         :score
         (score (:score db) bed-id))))
