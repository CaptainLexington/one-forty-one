(defproject one-forty-one-checkout "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229"]
                 [reagent "0.6.0"]
                 [re-frame "0.9.2"]
                 [re-frisk "0.3.2"]
                 [garden "1.3.2"]
                 [cljsjs/hammer "2.0.4-5"]
                 [ns-tracker "0.3.0"]]

  :plugins [[lein-cljsbuild "1.1.4"]
            [lein-garden "0.2.8"]
            [lein-less "1.7.5"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"
                                    "test/js"
                                    "resources/public/css"]

  :figwheel {:css-dirs ["resources/public/css"] }

  :garden {:builds [{:id           "screen"
                     :source-paths ["src/clj"]
                     :stylesheet   one-forty-one-checkout.css/screen
                     :compiler     {:output-to     "resources/public/css/screen.css"
                                    :pretty-print? true}}]}

  :less {:source-paths ["less"]
         :target-path  "resources/public/css"}

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "0.8.2"]]

    :plugins      [[lein-figwheel "0.5.9"]
                   [lein-doo "0.1.7"]]
    }}

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "one-forty-one-checkout.core/mount-root"
                    :websocket-host :js-client-host }
     :compiler     {:main                 one-forty-one-checkout.core
                    :output-to            "cordova/www/js/app.js"
                    :output-dir           "cordova/www/js/out"
                    :asset-path           "js/out"
                    :source-map-timestamp true
                    :preloads             [devtools.preload]
                    :external-config      {:devtools/config {:features-to-install :all}}
                    }}

    {:id           "min"
     :source-paths ["src/cljs"]
     :compiler     {:main            one-forty-one-checkout.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}

    {:id           "test"
     :source-paths ["src/cljs" "test/cljs"]
     :compiler     {:main          one-forty-one-checkout.runner
                    :output-to     "resources/public/js/compiled/test.js"
                    :output-dir    "resources/public/js/compiled/test/out"
                    :optimizations :none}}
    ]}

  )
