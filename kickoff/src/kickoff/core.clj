(ns kickoff.core
  (:require
    [environ.core :refer [env]]
    [onyx.api :as onyx])
  (:gen-class))

;; ---- Workflow --------------------------------------------------------------
(def ^:private workflow
  [[:input :increment]
   [:increment :output]])

;; ---- Catalog ---------------------------------------------------------------
(def ^:private catalog
  [{:onyx/name :input
    :onyx/plugin :onyx.plugin.seq/input
    :onyx/type :input
    :onyx/medium :seq
    :onyx/required-tags [:simple]
    :onyx/batch-size 1
    :onyx/max-peers 1}

   {:onyx/name :increment
    :onyx/type :function
    :onyx/fn :special.tasks.increment/increment-number
    :onyx/required-tags [:special]
    :onyx/batch-size 1
    :onyx/max-peers 1}

   {:onyx/name :output
    :onyx/plugin :onyx.peer.function/function
    :onyx/type :output
    :onyx/medium :function
    :onyx/fn :simple.tasks.output/output-seg
    :onyx/required-tags [:simple]
    :onyx/batch-size 1
    :onyx/max-peers 1}])

;; ---- Lifecycles ------------------------------------------------------------
(def ^:private lifecycles
  [{:lifecycle/task :input
    :lifecycle/calls :simple.tasks.input/emit-input-lifecycle}

   {:lifecycle/task :input
    :lifecycle/calls :onyx.plugin.seq/reader-calls}])

;; ---- Flow-Conditions -------------------------------------------------------
(def ^:private flow-conditions
  [])

;; ---- Peer-Config -----------------------------------------------------------
(defn- build-peer-config [tenancy-id zookeeper-addresses]
  {:onyx/tenancy-id tenancy-id
   :zookeeper/address zookeeper-addresses
   :onyx.peer/job-scheduler :onyx.job-scheduler/balanced})

;; ---- Kick-Off + Main -------------------------------------------------------
(defn- kick-off-job [config]
  (let [peer-config (:peers config)
        catalog (:catalog config)
        workflow (:workflow config)
        lifecycles (:lifecycles config)
        flow-conditions (:flow-conditions config)]
    (onyx/submit-job
       peer-config
       {:catalog catalog
        :workflow workflow
        :lifecycles lifecycles
        :flow-conditions flow-conditions
        :task-scheduler :onyx.task-scheduler/balanced})))

(defn -main [& args]
  (let [tenancy-id (:tenancy-id env)
        zookeeper-address (:zookeeper-addr env)]
    (kick-off-job {:peers (build-peer-config tenancy-id zookeeper-address)
                   :catalog catalog
                   :workflow workflow
                   :lifecycles lifecycles
                   :flow-conditions flow-conditions})))
