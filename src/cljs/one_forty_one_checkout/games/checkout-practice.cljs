(ns one-forty-one-checkout.games.checkout-practice
  (:require [one-forty-one-checkout.beds :as beds]
            [clojure.set :as set]
            [clojure.reader :refer [read-string]]))


(def checkout-values
  (into [] 
        (set/difference
          (into #{} (range 2 170))
          #{169 168 166 165 163 162 159})))


(defn evaluate-bed [bed-id]
  (cond
    (= bed-id ::beds/BULL) 25
    (= bed-id ::beds/CORK) 50
    :else (let [numerical-value (read-string (re-find #"\d+" (name bed-id)))]
            (cond
              (isa? bed-id ::beds/single) numerical-value
              (isa? bed-id ::beds/double) (* 2 numerical-value)
              (isa? bed-id ::beds/treble) (* 3 numerical-value)
              :else 0))))

(defn next-turn []
  (let [new-checkout (rand-nth checkout-values)]
    {:current-checkout new-checkout
     :current-value  new-checkout
     :on-dart 1}))

(defn succeed-attempt [score]
  (merge (next-turn)
         {:successes (+ 1 (:successes score))
          :total-attempts (+ 1 (:total-attempts score)) }))

(defn fail-attempt [score]
  (merge (next-turn)
         {:successes (:successes score)
          :total-attempts (+ 1 (:total-attempts score)) }))

(defn score [score bed-id]
  (let [bed-value (evaluate-bed bed-id)
        new-value (- (:current-value score) bed-value)]
    (cond
      (and (= new-value 0) (isa? bed-id ::beds/double))
      (succeed-attempt score)
      (>= 0 new-value)
      (fail-attempt score)
      (and (> new-value 0) (= 3 (:on-dart score)))
      (fail-attempt score)
      :else
      (assoc score
             :current-value new-value
             :on-dart (+ (:on-dart score) 1)))))
