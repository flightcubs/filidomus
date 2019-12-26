(ns flightcubs.filidomus.core
  (:require [flightcubs.filidomus.views :as views]
            [flightcubs.filidomus.events :as events]
            [flightcubs.filidomus.subs :as subs]
            [flightcubs.filidomus.utils :as utils]
            [flightcubs.filidomus.router :as router]
            [reagent.core :as reagent]
            [re-frame.core :as rf]))

(def dom-root (js/document.getElementById "app"))

(defn mount-app []
  (.log js/console "mounting app")
  (rf/clear-subscription-cache!)
  (router/start!)
  (reagent/render [views/main-view] dom-root))

(defn ^:export init
  "Initializes the app. Called from index.html with init()"
  []
  (rf/dispatch-sync [:initialize-db])
  (mount-app))
