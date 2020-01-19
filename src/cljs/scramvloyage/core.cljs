(ns scramvloyage.core
  (:require [reagent.core :as r :refer [atom]]
            [ajax.core :refer [GET]]))

(enable-console-print!)

(defonce page-state (atom {}))
;; better with config/env variables
(def server "http://localhost:10555")

(defn scramble? [params]
  (GET (str server "/scramble")
       {:params params
        :handler (fn [res] (swap! page-state #(assoc % :result res)))
        :error-handler (fn [{:keys [status status-text]}]
                         (swap! page-state #(assoc % :result (str status " " status-text))))}))

(defn header []
  [:div {:class "hero-body"}
   [:h1 {:class "title has-text-white has-text-centered"} "Scramble"]])

(defn input [label]
  [:div {:class "field"}
   [:label {:class "label"} (clojure.string/capitalize label)]
   [:div {:class "control"}
    [:input {:id (str label "-input")
             :class "input"
             :type "text"
             :placeholder "[a-z]"
             :on-change (fn [e]
                          (swap! page-state #(assoc % :result nil))
                          (swap! page-state #(assoc % (keyword label)
                                                    (->> e .-target .-value))))}]]])
(defn result-message []
  [:div {:class "container has-text-centered is-fluid"}
   (let [is-error? (fn [] (string? (:result @page-state)))]
     (cond
       (nil? (:result @page-state)) nil
       (is-error?) [:div {:class  "message has-background-danger"}
                    (str (:result @page-state))]
       :else [:div {:class (str "message " (if (:result @page-state)
                                             "has-background-success"
                                             "has-background-danger"))}
              (str (:result @page-state))])
     )])

(defn form []
  [:div {:class "container control is-fluid"}
   [input "letters"]
   [input "word"]
   [:div {:class "level control"}
    [:button {:class "button"
              :on-click #(scramble? (select-keys @page-state [:letters :word]))}
     "Scramble?"]
    [result-message]
    ]
   ]
  )

(defn footer []
  [:div {:class "has-text-centered"} "Samuel Falcón - 2020" ])

(defn page []
  [:section {:class "hero is-primary"}
   [:div {:class "hero-header"}
    [header]]
   [:div {:class "hero-body"}
    [form]]
   [:div {:class "hero-footer"}
    [footer]]])

(defn render []
  (r/render [page]
            (js/document.getElementById "app")))
