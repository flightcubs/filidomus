(ns flightcubs.filidomus.events
  (:require [flightcubs.filidomus.router :as router]
            [re-frame.core :as rf]))

(def default-db
  {:current-route nil})

(rf/reg-event-db
  :initialize-db
  (fn [_ [_ _]] default-db))

(rf/reg-event-fx
  :navigate!
  (fn [_ [_ & route]]
    {:navigate-fx! route}))

(rf/reg-fx
  :navigate-fx!
  (fn [route]
    (router/push-state! route)))

(rf/reg-event-db
  :navigated
  (fn [db [_ new-match]]
    (assoc db :current-route new-match)))
