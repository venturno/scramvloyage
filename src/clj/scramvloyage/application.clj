(ns scramvloyage.application
  (:gen-class)
  (:require [com.stuartsierra.component :as component]
            [scramvloyage.components.server-info :refer [server-info]]
            [system.components.endpoint :refer [new-endpoint]]
            [system.components.handler :refer [new-handler]]
            [system.components.middleware :refer [new-middleware]]
            [system.components.jetty :refer [new-web-server]]
            [scramvloyage.config :refer [config]]
            [scramvloyage.routes :refer [app-routes]]))

(defn app-system [config]
  (component/system-map
   :routes     (new-endpoint app-routes) 
   :middleware (new-middleware {:middleware (:middleware config)})
   :handler    (-> (new-handler)
                   (component/using [:routes :middleware]))
   :http       (-> (new-web-server (:http-port config))
                   (component/using [:handler]))
   :server-info (server-info (:http-port config))))

(defn -main [& _]
  (let [config (config)]
    (-> config
        app-system
        component/start)))
