(ns one-forty-one-checkout.events
    (:require [re-frame.core :as re-frame]
              [one-forty-one-checkout.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))
