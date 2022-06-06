(ns flightcubs.refilip.views
  (:require [flightcubs.refilip.utils :refer [<sub >evt]]
            [flightcubs.refilip.components.icon :as icon]))

(defn contact-at [logo link link-text]
  [:div.flex.flex-wrap.items-center.mb-3
   [:div.flex-shrink-0.mr-2
    [logo]]
   [:div
    [:a.text-lg.text-accent.font-mono.no-underline {:href link} link-text]]])

(defn home-page []
  [:div.container.max-w-xl.text-primary.font-sans.leading-tight
   [:h1.text-5xl.mt-12 "Hi! 👋"]
   [:p.text-3xl.mt-4 "I'm Filip - A software engineer, designer and product person based in Stockholm."]
   [:p.text-lg.mt-4 "Read my " [:a {:href "/#/blog"} "blog"] ", explore the " [:a {:href "https://github.com/flightcubs/refilip"} "source code"] " of this website or find me at:"]
   [:div.mt-4
    [contact-at icon/linkedin "https://linkedin.com/in/filiphedberg/" "linkedin.com/in/filiphedberg"]
    [contact-at icon/github "https://github.com/flightcubs" "github.com/flightcubs"]
    [contact-at icon/email "mailto:flightcubs@gmail.com" "flightcubs@gmail.com"]
    [contact-at icon/twitter "https://twitter.com/flightcubs" "@flightcubs"]]])

(defn minimize-menu [_e]
  (>evt [:toggle-menu-to false]))

(defn header-links []
  [:div.w-full.flex-grow.md:flex.md:items-center.md:w-auto {:class (if (<sub [:menu-expanded]) "block" "hidden")}
   [:div.text-sm.md:flex-grow.font-sans
    [:a.block.mt-4.md:inline-block.md:mt-0.text-primary.hover:text-accent.mr-4.no-underline {:href "/#/" :on-click minimize-menu} "Home"]
    [:a.block.mt-4.mb-4.md:mb-0.md:inline-block.md:mt-0.text-primary.hover:text-accent.no-underline {:href "/#/blog" :on-click minimize-menu} "Blog"]]])

(defn header []
  [:nav.max.w-full.bg-white.border-b.border-gray-200.flex.justify-between.fixed.z-10.top-0
   [:div.w-full.flex.items-center.justify-between.flex-wrap.flex-shrink-0.pl-4.pr-4
    [icon/header]
    [icon/header-menu #(>evt [:toggle-menu])]
    [header-links]]])

(defn main-view []
  (let [current-route (<sub [:current-route])]
    [:div
     [header]
     [:div.w-full.mx-auto.px-6.pt-20
      (when current-route
        [(-> current-route :data :view)])]]))
