(ns one-forty-one-checkout.games.checkout-practice
  (:require [one-forty-one-checkout.beds :as beds]
            [clojure.reader :refer [read-string]]))

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

(defn succeed-attempt [score]
  {:current-checkout 141
   :current-value 141
   :on-dart 1
   :successes (+ 1 (:successes score))
   :total-attempts (+ 1 (:total-attempts score))
   })

(defn fail-attempt [score]
  {:current-checkout 141
   :current-value 141
   :on-dart 1
   :successes (:successes score)
   :total-attempts (+ 1 (:total-attempts score))
   })


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
