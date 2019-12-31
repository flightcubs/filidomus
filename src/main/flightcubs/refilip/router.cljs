(ns flightcubs.refilip.router
  (:require [flightcubs.refilip.utils :refer [<sub >evt]]
            [flightcubs.refilip.views :as views]
            [reitit.core :as reitit]
            [reitit.frontend :as reitit-frontend]
            [reitit.frontend.easy :as reitit-frontend-easy]))

(def routes
  ["/"
   [""
    {:name      ::home
     :view      views/home-page
     :link-text "Home"}
    ]
   ["blog"
    [""
     {:name      ::blog
      :view      views/blog
      :link-text "Home"}]
    ["/post/:slug"
     {:name      ::blog-post
      :view      views/blog-post
      :link-text "Home"}
     ]
    ]
   ])

(def router
  (reitit-frontend/router routes))

(defn on-navigate [new-match]
  (when new-match
    (>evt [:navigated new-match])))

(defn start! []
  (reitit-frontend-easy/start!
    router
    on-navigate
    {:use-fragment true}))

(defn push-state! [route]
  (apply reitit-frontend-easy/push-state route))

(defn url-from-name [name]
  (:path (reitit/match-by-path router name)))

(defn path-by-name
  ([name]
   (path-by-name name {}))
  ([name path-params]
   (-> router
       (reitit/match-by-name name path-params)
       :path)))
