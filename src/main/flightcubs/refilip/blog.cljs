(ns flightcubs.refilip.blog
  (:require [flightcubs.refilip.utils :refer [<sub]]
            [shadow.resource :as rc]
            [reagent.core :as r]
            ["react-markdown" :as ReactMarkdown]))

(def markdown (r/adapt-react-class ReactMarkdown))
(def posts
  {:test-markdown {:name     "Markdown test"
                   :hidden?  true
                   :date     "2119-12-29"
                   :markdown (rc/inline "./posts/test-markdown.md")}
   :hello-world   {:name     "Hello, World!"
                   :date     "2019-11-29"
                   :markdown (rc/inline "./posts/hello-world.md")}
   :growth-2020   {:name     "A year of growth"
                   :date     "2020-01-01"
                   :markdown (rc/inline "./posts/growth-2020.md")}
   :hello-again   {:name     "Hello again, World!"
                   :date     "2022-06-06"
                   :markdown (rc/inline "./posts/hello-again.md")}})

(defn posts-by-date [posts]
  (reverse (sort-by (fn [[_slug post]] (:date post)) posts)))

(defn future-date? [iso-date]
  (> (.parse js/Date iso-date) (.now js/Date)))

(defn show-post [[_slug post]]
  (and (not (future-date? (:date post))) (not (:hidden? post))))

(defn blog-post-link [slug post]
  [:a.font-sans {:href (str "/#/blog/post/" (name slug))}
   (str (:date post) ": " (:name post))])

(defn blog []
  [:div
   [:h1.font-sans.text-3xl.text-primary "Blog"]
   [:div.mt-4.space-y-2 (for [[slug post] (->> posts (filter show-post) posts-by-date)]
                          [:div {:key slug}
                           [blog-post-link slug post]])]])

(defn blog-post []
  (let [post (->> (<sub [:path-params]) :slug keyword (get posts)) content (:markdown post) date (:date post)]
    [:div.markdown.max-w-2xl.mx-auto.mb-24
     [:p.font-mono.text-primary.opacity-50 date]
     [markdown content]]))
