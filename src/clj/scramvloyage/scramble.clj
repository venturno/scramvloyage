(ns scramvloyage.scramble
  (:require [clojure.spec.alpha :as s]))

(s/def ::spec (s/and string? #(re-matches #"[a-z]+" %)))

(defn v1 [letters checked-word]
  (let [letter-freqs (frequencies letters)
        word-freqs (frequencies checked-word)
        same-freq? (fn [k]
                     (<= (get word-freqs k 0)
                         (get letter-freqs k 0)))]
    (every? same-freq? (keys word-freqs))))

(defn v2 [letters checked-word]
  (let [freqs (->> (frequencies checked-word)
                   (into (hash-map)) transient)]
    (doseq [ch letters]
      (assoc! freqs ch (dec (get freqs ch 0))))
    (not-any? pos? (vals (persistent! freqs)))))

(defn negative-frequencies [coll]
  "Returns a map from distinct items in coll to the number of times they appear, but negative. See `clojure.core/frequencies`"
  (persistent!
   (reduce (fn [counts x]
             (assoc! counts x (dec (get counts x 0))))
           (transient {}) coll)))

(defn v3 [letters checked-word]
  (let [freq1 (frequencies letters)
        freq2 (negative-frequencies checked-word)
        negative-entry? (fn [[k v]] (neg? v))]
    (->> (merge-with + freq1 freq2)
         (not-any? negative-entry?))))

(def scramble? v3)
