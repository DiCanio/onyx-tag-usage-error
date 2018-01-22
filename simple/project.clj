(defproject simple "0.1.0"
  :description "Simple project that contains common tasks."

  :dependencies [[environ "1.0.1"]
                 [org.clojure/clojure "1.9.0"]
                 [org.onyxplatform/onyx "0.12.2"]]

  :main ^:skip-aot simple.core

  :profiles {:uberjar {:aot [simple.core]}})
