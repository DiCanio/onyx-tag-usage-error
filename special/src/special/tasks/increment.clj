(ns special.tasks.increment)

(defn increment-number
  "Increments the number stored within the segment at :number"
  [segment]
  {:number (inc (:number segment))})
