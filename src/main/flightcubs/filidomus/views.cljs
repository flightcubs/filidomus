(ns flightcubs.filidomus.views
  (:require [flightcubs.filidomus.utils :refer [<sub >evt]]
            [shadow.resource :as rc]
            ["react-markdown/with-html" :as react-markdown]))

(def md-test "# This is a header\n\nAnd this is a paragraph")
(def media-folder "/media")

(defn home-page []
  [:div
   [:h1 "Hello world!"]])

(def posts
  {:hello-blog      {:name     "Hello blog!"
                     :subtitle "Everything starts with a purpose"
                     :date     "2019-12-27"
                     :markdown (rc/inline "./posts/test.md")}
   :building-a-blog {:name     "Building this blog"
                     :date     "2019-12-29"
                     :markdown (rc/inline "./posts/test.md")}
   :first-post      {:name     "First post"
                     :date     "2019-11-29"
                     :markdown (rc/inline "./posts/test.md")}
   })

(defn posts-by-date [posts]
  (sort-by (fn [[slug post]] (:date post)) posts))

(defn future-date? [iso-date]
  (> (.parse js/Date iso-date) (.now js/Date)))

(defn show-post [[slug post]]
  (not (future-date? (:date post))))

(defn blog-post-link [slug post]
  [:a {:href (str "/#/blog/post/" (name slug))}
   (str (:date post) " - " (:name post))])

(defn blog []
  [:div
   [:h1 "Blog"]
   (for [[slug post] (filter show-post (posts-by-date posts))]
     [:div
      [blog-post-link slug post]])])

(defn blog-post []
  (let [content (->> (<sub [:path-params]) :slug keyword (get posts) :markdown)]
    [:div
     [:> react-markdown content]]))

(defn main-view []
  (let [current-route (<sub [:current-route])]
    [:div
     (when current-route
       [(-> current-route :data :view)])]))

