(ns flightcubs.refilip.utils
  (:require [re-frame.core :as rf]))

; Defining shorthands for re-frame subscribe/dispatch
(def <sub (comp deref rf/subscribe))
(def >evt rf/dispatch)
