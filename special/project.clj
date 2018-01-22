(defproject special "0.1.0"
  :description "Project that contains a special task."

  :dependencies [[environ "1.0.1"]
                 [org.clojure/clojure "1.9.0"]
                 [org.onyxplatform/onyx "0.12.2"]]

  :main ^:skip-aot special.core

  :profiles {:uberjar {:aot [special.core]}})
