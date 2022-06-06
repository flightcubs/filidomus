(ns flightcubs.refilip.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 :current-route
 (fn [db]
   (:current-route db)))

(rf/reg-sub
 :path-params
 :<- [:current-route]
 (fn [current-route]
   (:path-params current-route)))

(rf/reg-sub
 :menu-expanded
 (fn [db]
   (:menu-expanded db)))
