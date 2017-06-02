(ns one-forty-one-checkout.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [one-forty-one-checkout.core-test]))

(doo-tests 'one-forty-one-checkout.core-test)
