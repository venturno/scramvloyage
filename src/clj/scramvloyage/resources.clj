(ns scramvloyage.resources
  (:require [liberator.core :refer [defresource]]
            [scramvloyage.scramble   :as scramble :refer [scramble?]]
            [clojure.spec.alpha      :as s]))

(declare scramble)

(defn valid-request? [{:keys [letters word]}]
  (and (s/valid? ::scramble/spec letters)
       (s/valid? ::scramble/spec word)))

(defresource scramble
  {:allowed-methods [:get]
   :available-media-types ["application/json"]
   :malformed? (fn [ctx]
                 (not (valid-request?
                       (get-in ctx [:request :params]))))
   :handle-ok (fn [ctx]
                (let [{:keys [letters word]} (get-in ctx [:request :params])]
                  (str (scramble? letters word))))})
