(defproject kickoff "0.1.0"
  :description "Project kicking off an onyx job..."

  :dependencies [[environ "1.0.1"]
                 [org.clojure/clojure "1.9.0"]
                 [org.clojure/tools.cli "0.3.5"]
                 [org.onyxplatform/onyx "0.12.2"]]

  :main ^:skip-aot kickoff.core

  :profiles {:uberjar {:aot [kickoff.core]}})
