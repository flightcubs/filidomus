(ns flightcubs.filidomus.views
  (:require [flightcubs.filidomus.utils :refer [<sub >evt]]))

(defn home-page []
  [:div
   [:h1 "Hello world!"]])

(defn main-view []
  (let [current-route (<sub [:current-route])]
    [:div
     (when current-route
       [(-> current-route :data :view)])]))