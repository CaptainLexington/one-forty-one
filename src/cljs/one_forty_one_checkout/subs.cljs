(ns one-forty-one-checkout.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 :game
 (fn [db]
   (:game db)))

(re-frame/reg-sub
 :selected-bed
 (fn [db]
   (:selected-bed db)))
