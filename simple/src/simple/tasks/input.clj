(ns simple.tasks.input
  (:require
    [environ.core :refer [env]]
    [onyx.plugin.seq]))

(defn emit-input
  "Emits some segments as input of the pipeline."
  [_ _]
  {:seq/seq [{:number 1} {:number 50}]})

; ---- Onyx-Related -----------------------------------------------------------
(def emit-input-lifecycle
  {:lifecycle/before-task-start emit-input})
