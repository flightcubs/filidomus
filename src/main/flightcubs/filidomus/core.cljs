(ns flightcubs.filidomus.core
  (:require [flightcubs.filidomus.views :as views]
            [flightcubs.filidomus.events :as events]
            [flightcubs.filidomus.subs :as subs]
            [flightcubs.filidomus.utils :as utils]
            [flightcubs.filidomus.router :as router]
            [reagent.core :as reagent]
            [re-frame.core :as rf]))

(defn mount-app []
  (reagent/render [views/main-view] (.getElementById js/document "app")))

(defn ^:export init
  "Initializes the app. Called from index.html with init()"
  []
  (router/start!)
  (rf/dispatch-sync [:initialize-db])
  (mount-app))
