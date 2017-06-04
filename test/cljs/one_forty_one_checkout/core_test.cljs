(ns one-forty-one-checkout.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [one-forty-one-checkout.core :as core]
            [one-forty-one-checkout.beds :as beds]
            ))

(deftest beds
  (testing "beds is beds"
    (is (isa? ::beds/T20 ::beds/treble))
    (is (isa? ::beds/T20 ::beds/bed))
    (is (not (isa? ::beds/T20 ::beds/double)))
    (is (isa? ::beds/BULL ::beds/single))
    (is (isa? ::beds/BULL ::beds/bullseye))))
