(ns flightcubs.refilip.core
  (:require [flightcubs.refilip.views :as views]
            [flightcubs.refilip.events :as events]
            [flightcubs.refilip.subs :as subs]
            [flightcubs.refilip.utils :as utils]
            [flightcubs.refilip.router :as router]
            [reagent.dom :as rdom]
            [re-frame.core :as rf]))

(def dom-root (js/document.getElementById "app"))

(defn mount-app []
  (.log js/console "mounting app")
  (rf/clear-subscription-cache!)
  (router/start!)
  (rdom/render [views/main-view] dom-root))

(defn ^:export init
  "Initializes the app. Called from index.html with init()"
  []
  (rf/dispatch-sync [:initialize-db])
  (mount-app))
