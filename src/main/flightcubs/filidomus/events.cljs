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

(rf/reg-event-db
  :toggle-menu
  (fn [db [_ _]]
    (assoc db :menu-expanded (not (:menu-expanded db)))))

(rf/reg-event-db
  :toggle-menu-to
  (fn [db [_ bool]]
    (assoc db :menu-expanded bool)))
