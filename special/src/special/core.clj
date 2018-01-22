(ns special.core
  (:require
    [environ.core :refer [env]]
    [onyx.api :as onyx]
    [special.tasks.increment])
  (:gen-class))

(defn peer-config [{:keys [tenancy-id zookeeper-addr bind-addr]}]
  (assert tenancy-id)
  (assert zookeeper-addr)
  (assert bind-addr)
  {:onyx/tenancy-id tenancy-id
   :zookeeper/address zookeeper-addr
   :onyx.peer/job-scheduler :onyx.job-scheduler/balanced
   :onyx.peer/tags [:special]
   :onyx.messaging/bind-addr bind-addr
   :onyx.messaging/peer-port 40200
   :onyx.messaging/impl :aeron
   :onyx.messaging.aeron/embedded-driver? true
   :onyx.monitoring/config {:monitoring :no-op}
   :onyx.log/config {}})

(defn -main [& args]
  (let [peer-config (peer-config env)
        num-vpeers (Integer/parseInt (:num-vpeers env))
        peer-group (onyx/start-peer-group peer-config)]
    (onyx/start-peers num-vpeers peer-group)))
