(ns flightcubs.filidomus.views
  (:require [flightcubs.filidomus.utils :refer [<sub >evt]]
            [shadow.resource :as rc]
            ["react-markdown/with-html" :as react-markdown]))

(def md-test "# This is a header\n\nAnd this is a paragraph")
(def media-folder "/media")

(defn home-page []
  [:div
   [:h1 "Hello world!"]
   [:p "test"]
   [:> react-markdown {:source md-test}]
   ])

(defn blog []
  [:div
   [:h1 "Blog"]])


(def posts
  {:my-first-post {:name     "My first post"
                   :date     "2019-12-26"
                   :markdown (rc/inline "./posts/test.md")}})


(defn blog-post []
  (let [content (->> (<sub [:path-params])
                     :slug
                     keyword
                     (get posts)
                     :markdown)]
    [:div
     [:> react-markdown content]
     ]))

(defn main-view []
  (let [current-route (<sub [:current-route])]
    [:div
     (when current-route
       [(-> current-route :data :view)])]))