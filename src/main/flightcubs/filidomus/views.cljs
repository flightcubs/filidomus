(ns flightcubs.filidomus.views
  (:require [flightcubs.filidomus.utils :refer [<sub >evt]]
            [shadow.resource :as rc]
            ["react-markdown/with-html" :as react-markdown]))

(def md-test "# This is a header\n\nAnd this is a paragraph")
(def media-folder "/media")
(def github-repo "https://github.com/flightcubs/filidomus")

(defn logo-linkedin []
  [:svg.fill-current {:width "24" :height "24" :viewBox "0 0 24 24" :fill "none" :xmlns "http://www.w3.org/2000/svg"}
   [:path {:d "M20.447 20.452H16.893V14.883C16.893 13.555 16.866 11.846 15.041 11.846C13.188 11.846 12.905 13.291 12.905 14.785V20.452H9.351V9H12.765V10.561H12.811C13.288 9.661 14.448 8.711 16.181 8.711C19.782 8.711 20.448 11.081 20.448 14.166V20.452H20.447ZM5.337 7.433C4.193 7.433 3.274 6.507 3.274 5.368C3.274 4.23 4.194 3.305 5.337 3.305C6.477 3.305 7.401 4.23 7.401 5.368C7.401 6.507 6.476 7.433 5.337 7.433ZM7.119 20.452H3.555V9H7.119V20.452ZM22.225 0H1.771C0.792 0 0 0.774 0 1.729V22.271C0 23.227 0.792 24 1.771 24H22.222C23.2 24 24 23.227 24 22.271V1.729C24 0.774 23.2 0 22.222 0H22.225Z" :fill "#2A3947"}]])

(defn logo-github []
  [:svg.fill-current {:width "24" :height "24" :viewBox "0 0 24 24" :fill "none" :xmlns "http://www.w3.org/2000/svg"}
   [:path {:d "M12 0.296997C5.37 0.296997 0 5.67 0 12.297C0 17.6 3.438 22.097 8.205 23.682C8.805 23.795 9.025 23.424 9.025 23.105C9.025 22.82 9.015 22.065 9.01 21.065C5.672 21.789 4.968 19.455 4.968 19.455C4.422 18.07 3.633 17.7 3.633 17.7C2.546 16.956 3.717 16.971 3.717 16.971C4.922 17.055 5.555 18.207 5.555 18.207C6.625 20.042 8.364 19.512 9.05 19.205C9.158 18.429 9.467 17.9 9.81 17.6C7.145 17.3 4.344 16.268 4.344 11.67C4.344 10.36 4.809 9.29 5.579 8.45C5.444 8.147 5.039 6.927 5.684 5.274C5.684 5.274 6.689 4.952 8.984 6.504C9.944 6.237 10.964 6.105 11.984 6.099C13.004 6.105 14.024 6.237 14.984 6.504C17.264 4.952 18.269 5.274 18.269 5.274C18.914 6.927 18.509 8.147 18.389 8.45C19.154 9.29 19.619 10.36 19.619 11.67C19.619 16.28 16.814 17.295 14.144 17.59C14.564 17.95 14.954 18.686 14.954 19.81C14.954 21.416 14.939 22.706 14.939 23.096C14.939 23.411 15.149 23.786 15.764 23.666C20.565 22.092 24 17.592 24 12.297C24 5.67 18.627 0.296997 12 0.296997" :fill "#2A3947"}]])

(defn logo-email []
  [:svg.fill-current {:width "24" :height "24" :viewBox "0 0 24 24" :fill "none" :xmlns "http://www.w3.org/2000/svg"}
   [:path {:d "M24 4.5V19.5C24 20.35 23.35 21 22.5 21H21V7.387L12 13.85L3 7.387V21H1.5C0.649 21 0 20.35 0 19.5V4.5C0 4.075 0.162 3.7 0.431 3.432C0.7 3.16 1.076 3 1.5 3H2L12 10.25L22 3H22.5C22.925 3 23.3 3.162 23.569 3.432C23.839 3.7 24 4.075 24 4.5Z" :fill "#2A3947"}]])

(defn logo-twitter []
  [:svg.fill-current {:width "24" :height "24" :viewBox "0 0 24 24" :fill "none" :xmlns "http://www.w3.org/2000/svg"}
   [:g {:clip-path "url(#clip0)"}
    [:path {:d "M23.954 4.56897C23.069 4.95797 22.124 5.22297 21.129 5.34397C22.143 4.73297 22.923 3.76997 23.292 2.62097C22.341 3.17597 21.287 3.57997 20.165 3.80497C19.269 2.84597 17.992 2.24597 16.574 2.24597C13.857 2.24597 11.654 4.44897 11.654 7.16297C11.654 7.55297 11.699 7.92797 11.781 8.28697C7.691 8.09397 4.066 6.12997 1.64 3.16097C1.213 3.88297 0.974 4.72197 0.974 5.63597C0.974 7.34597 1.844 8.84897 3.162 9.73197C2.355 9.70597 1.596 9.48397 0.934 9.11597V9.17697C0.934 11.562 2.627 13.551 4.88 14.004C4.467 14.115 4.031 14.175 3.584 14.175C3.27 14.175 2.969 14.145 2.668 14.089C3.299 16.042 5.113 17.466 7.272 17.506C5.592 18.825 3.463 19.611 1.17 19.611C0.78 19.611 0.391 19.588 0 19.544C2.189 20.938 4.768 21.753 7.557 21.753C16.611 21.753 21.556 14.257 21.556 7.76697C21.556 7.55797 21.556 7.34697 21.541 7.13697C22.502 6.44797 23.341 5.57697 24.001 4.58897L23.954 4.56897Z" :fill "#2A3947"}]]
   [:defs
    [:clipPath#clip0
     [:path {:d "M0 0H24V24H0V0Z" :fill "white"}]]]])

(defn contact-at [logo link link-text]
  [:div.flex.flex-wrap.items-center.mb-3
   [:div.flex-shrink-0.mr-2
    [logo]]
   [:div
    [:a.text-lg.text-accent.font-mono.no-underline {:href link} link-text]]
   ])

(defn home-page []
  [:div.container.max-w-xl.text-primary.leading-tight
   [:h1.font-sans.text-5xl.mt-12 "Hi! 👋"]
   [:p.font-sans.text-3xl.mt-4 "I’m Filip - A software engineer, designer and product person based in Stockholm."]
   [:p.font-sans.text-lg.mt-4 "Read my " [:a {:href "/#/blog"} "blog"] ", explore the " [:a {:href github-repo} "source code"] " of this website or find me at:"]
   [:div.mt-4
    [contact-at logo-linkedin "https://linkedin.com/in/filiphedberg/" "linkedin.com/in/filiphedberg"]
    [contact-at logo-github "https://github.com/flightcubs" "github.com/flightcubs"]
    [contact-at logo-email "mailto:flightcubs@gmail.com" "flightcubs@gmail.com"]
    [contact-at logo-twitter "https://twitter.com/flightcubs" "@flightcubs"]]
   ])

(def posts
  {:hello-blog      {:name     "Hello blog!"
                     :subtitle "Everything starts with a purpose"
                     :date     "2119-12-27"
                     :markdown (rc/inline "./posts/test.md")}
   :building-a-blog {:name     "Building this blog"
                     :date     "2119-12-29"
                     :markdown (rc/inline "./posts/test.md")}
   :first-post      {:name     "Hello, World!"
                     :date     "2019-11-29"
                     :markdown (rc/inline "./posts/hello-world.md")}
   })

(defn posts-by-date [posts]
  (sort-by (fn [[slug post]] (:date post)) posts))

(defn future-date? [iso-date]
  (> (.parse js/Date iso-date) (.now js/Date)))

(defn show-post [[slug post]]
  (not (future-date? (:date post))))

(defn blog-post-link [slug post]
  [:a.font-sans {:href (str "/#/blog/post/" (name slug))}
   (str (:date post) " - " (:name post))])

(defn blog []
  [:div
   [:h1.font-sans.text-3xl "Blog"]
   (for [[slug post] (->> posts posts-by-date (filter show-post))]
     [:div {:key slug}
      [blog-post-link slug post]])])

(defn blog-post []
  (let [post (->> (<sub [:path-params]) :slug keyword (get posts)) content (:markdown post) date (:date post)]
    [:div.markdown.max-w-2xl.mx-auto.mb-24
     [:p.font-mono.text-primary.opacity-50 date]
     [:> react-markdown content]]))

(defn header-icon []
  [:div.flex.items-center.flex-shrink-0.text-white.mr-6.h-12
   [:div.flex-shrink-0.mr-6
    [:a {:href "/#/"}
     [:svg.h-9.w-auto.hidden.md:block {:width "80" :height "35" :viewBox "0 0 80 35" :fill "none" :xmlns "http://www.w3.org/2000/svg"}
      [:path {:d "M5.50586 23.4688C5.50586 24.2526 5.52409 24.763 5.56055 25H2.15625V10.7129H5.50586V11.7656C6.2806 10.8815 7.0918 10.4395 7.93945 10.4395C8.79622 10.4395 9.4707 10.5443 9.96289 10.7539L9.7168 14.4316L9.66211 14.4727C9.3431 13.9076 8.68229 13.625 7.67969 13.625C7.29688 13.625 6.90495 13.7572 6.50391 14.0215C6.11198 14.2767 5.7793 14.6185 5.50586 15.0469V23.4688ZM10.9473 17.9727C10.9473 16.9062 11.1296 15.9173 11.4941 15.0059C11.8587 14.0944 12.3737 13.2969 13.0391 12.6133C14.4609 11.1641 16.3203 10.4395 18.6172 10.4395C20.5586 10.4395 22.1172 11.082 23.293 12.3672C24.4505 13.6068 25.0293 15.1654 25.0293 17.043C25.0293 17.763 24.9746 18.2552 24.8652 18.5195C23.9538 18.7747 22.099 18.9023 19.3008 18.9023H14.4883C14.7253 19.8776 15.2767 20.6387 16.1426 21.1855C17.0085 21.7233 18.1341 21.9922 19.5195 21.9922C20.9688 21.9922 22.2129 21.7324 23.252 21.2129C23.5254 21.0762 23.7305 20.9486 23.8672 20.8301C23.849 21.2129 23.8262 21.6048 23.7988 22.0059L23.6484 24.1387C22.9557 24.6126 21.8346 24.9408 20.2852 25.123C19.8294 25.1777 19.3919 25.2051 18.9727 25.2051C16.6576 25.2051 14.7435 24.5306 13.2305 23.1816C11.7083 21.8236 10.9473 20.0872 10.9473 17.9727ZM21.3926 16.25C21.0189 14.2904 19.9525 13.3105 18.1934 13.3105C16.8079 13.3105 15.7552 13.9076 15.0352 15.1016C14.8164 15.4661 14.6478 15.8672 14.5293 16.3047C14.748 16.3138 15.0169 16.3229 15.3359 16.332H16.3477C16.6849 16.3411 17.0085 16.3457 17.3184 16.3457H18.0977C18.5625 16.3457 19.0182 16.3411 19.4648 16.332L20.5859 16.291C20.8867 16.2819 21.1556 16.2682 21.3926 16.25ZM31.4141 14.4727C31.4141 14.6458 31.4141 14.974 31.4141 15.457C31.4141 15.931 31.4368 16.332 31.4824 16.6602H27.4902C27.5723 16.1042 27.6133 15.612 27.6133 15.1836C27.6133 14.7552 27.6087 14.4635 27.5996 14.3086C27.5996 14.1445 27.5951 13.985 27.5859 13.8301C27.5768 13.6751 27.5677 13.5293 27.5586 13.3926L27.5176 13.0508H31.4824C31.4551 13.3971 31.4414 13.6797 31.4414 13.8984L31.4141 14.4727ZM31.4141 22.8125C31.4141 22.9857 31.4141 23.3138 31.4141 23.7969C31.4141 24.2708 31.4368 24.6719 31.4824 25H27.4902C27.5723 24.444 27.6133 23.9518 27.6133 23.5234C27.6133 23.0951 27.6087 22.8034 27.5996 22.6484C27.5996 22.4844 27.5951 22.3249 27.5859 22.1699C27.5768 22.015 27.5677 21.8691 27.5586 21.7324L27.5176 21.3906H31.4824C31.4551 21.737 31.4414 22.0195 31.4414 22.2383L31.4141 22.8125ZM41.8047 12.2852C41.7318 12.5495 41.6953 13.2148 41.6953 14.2812V15.4844H38.4004V25H35.0508V15.4844H33.041C33.0501 14.8828 33.0592 14.3496 33.0684 13.8848L33.0957 12.4219V12.2852H35.0508V11.4375C35.0508 9.25 35.5612 7.49089 36.582 6.16016C37.6484 4.78385 39.1159 4.0957 40.9844 4.0957C41.7773 4.0957 42.3014 4.15039 42.5566 4.25977L42.5703 7.69141L42.543 7.75977C42.1966 7.41341 41.7546 7.24023 41.2168 7.24023C40.6882 7.24023 40.2598 7.33138 39.9316 7.51367C39.6035 7.68685 39.3255 7.9375 39.0977 8.26562C38.6328 8.92188 38.4004 9.81966 38.4004 10.959V12.2852H41.8047ZM43.8965 6.63867C44.6257 5.49935 45.3867 4.49674 46.1797 3.63086L49.1191 5.94141C48.7272 6.42448 48.3626 6.89844 48.0254 7.36328C48.0254 7.36328 47.6745 7.86003 46.9727 8.85352L43.8965 6.63867ZM47.4922 23.4688C47.4922 24.2526 47.5104 24.763 47.5469 25H44.0742V10.7129H47.4922V23.4688ZM54.5332 23.4688C54.5332 24.2526 54.5514 24.763 54.5879 25H51.1152V4.62891H54.5332V23.4688ZM57.9512 6.63867C58.6803 5.49935 59.4414 4.49674 60.2344 3.63086L63.1738 5.94141C62.7819 6.42448 62.4173 6.89844 62.0801 7.36328C62.0801 7.36328 61.7292 7.86003 61.0273 8.85352L57.9512 6.63867ZM61.5469 23.4688C61.5469 24.2526 61.5651 24.763 61.6016 25H58.1289V10.7129H61.5469V23.4688ZM68.9707 24.7129V28.7324C68.9707 29.5163 68.9889 30.0267 69.0254 30.2637H65.6211V10.7129H68.9707V11.6016C70.0736 10.8268 71.1309 10.4395 72.1426 10.4395C73.1543 10.4395 74.0521 10.5807 74.8359 10.8633C75.6198 11.1458 76.3079 11.5833 76.9004 12.1758C78.2038 13.4609 78.8555 15.293 78.8555 17.6719C78.8555 19.2487 78.4863 20.6569 77.748 21.8965C77.1191 22.9447 76.2487 23.7832 75.1367 24.4121C74.1523 24.9681 73.0859 25.2461 71.9375 25.2461C70.7891 25.2461 69.8001 25.0684 68.9707 24.7129ZM68.9707 21.2676C69.6634 21.8509 70.5612 22.1426 71.6641 22.1426C73.4688 22.1426 74.6354 21.2721 75.1641 19.5312C75.3464 18.9388 75.4375 18.3053 75.4375 17.6309C75.4375 16.9473 75.3737 16.4049 75.2461 16.0039C75.1185 15.5938 74.9499 15.2428 74.7402 14.9512C74.5397 14.6504 74.3118 14.4043 74.0566 14.2129C73.8014 14.0124 73.5417 13.8529 73.2773 13.7344C72.8125 13.5247 72.334 13.4199 71.8418 13.4199C71.3496 13.4199 70.8392 13.5339 70.3105 13.7617C69.7819 13.9896 69.3353 14.2949 68.9707 14.6777V21.2676Z" :fill "#2A3947"}]
      [:path {:d "M73 30.8796C73.6981 29.7888 74.4267 28.829 75.1859 28L78 30.212C77.6248 30.6745 77.2757 31.1283 76.9529 31.5733C76.9529 31.5733 76.6169 32.0489 75.945 33L73 30.8796Z" :fill "#DC8C89"}]
      [:path {:d "M2.8146 32.4916C2.27447 32.483 1.87008 32.4327 1.60142 32.3406C1.33275 32.2486 1.19936 32.1441 1.20122 32.0271C1.20332 31.8956 1.77941 31.7658 2.92949 31.6379C4.07935 31.5245 5.53302 31.4161 7.29051 31.3124C9.048 31.2088 10.9404 31.1146 12.9677 31.0299C15.0626 30.9463 16.9884 30.8746 18.7452 30.8148C20.5695 30.7561 22.0897 30.7072 23.3059 30.6681C24.5219 30.6436 25.1974 30.6324 25.3324 30.6346C25.6025 30.6389 26.3119 30.6209 27.4605 30.5807C28.609 30.5551 30.3992 30.5178 32.8311 30.4688C35.2629 30.4344 38.4037 30.3967 42.2537 30.3557C46.171 30.3303 50.9998 30.3122 56.7402 30.3012C57.3479 30.3109 58.124 30.3452 59.0685 30.4041C60.013 30.463 60.484 30.5729 60.4814 30.7337C60.4781 30.9384 59.8343 31.0818 58.5499 31.1637C57.2652 31.2602 55.6772 31.3227 53.7859 31.351C51.8943 31.394 49.9018 31.4135 47.8083 31.4094C45.7824 31.4064 43.9927 31.4144 42.4391 31.4336C40.2777 31.4576 38.319 31.4776 36.5629 31.4935C34.8065 31.524 33.0503 31.5473 31.2942 31.5632C29.6054 31.5948 27.8152 31.6321 25.9236 31.6751C24.0318 31.7327 21.9372 31.8017 19.6398 31.8821L2.8146 32.4916Z" :fill "#DC8C89"}]]
     [:svg.w-15.block.md:hidden {:width "47" :height "27" :viewBox "0 0 47 27" :fill "none" :xmlns "http://www.w3.org/2000/svg"}
      [:path {:d "M5.70898 23.4688C5.70898 24.2526 5.72721 24.763 5.76367 25H2.35938V10.7129H5.70898V11.7656C6.48372 10.8815 7.29492 10.4395 8.14258 10.4395C8.99935 10.4395 9.67383 10.5443 10.166 10.7539L9.91992 14.4316L9.86523 14.4727C9.54622 13.9076 8.88542 13.625 7.88281 13.625C7.5 13.625 7.10807 13.7572 6.70703 14.0215C6.3151 14.2767 5.98242 14.6185 5.70898 15.0469V23.4688ZM11.1504 17.9727C11.1504 16.9062 11.3327 15.9173 11.6973 15.0059C12.0618 14.0944 12.5768 13.2969 13.2422 12.6133C14.6641 11.1641 16.5234 10.4395 18.8203 10.4395C20.7617 10.4395 22.3203 11.082 23.4961 12.3672C24.6536 13.6068 25.2324 15.1654 25.2324 17.043C25.2324 17.763 25.1777 18.2552 25.0684 18.5195C24.1569 18.7747 22.3021 18.9023 19.5039 18.9023H14.6914C14.9284 19.8776 15.4798 20.6387 16.3457 21.1855C17.2116 21.7233 18.3372 21.9922 19.7227 21.9922C21.1719 21.9922 22.416 21.7324 23.4551 21.2129C23.7285 21.0762 23.9336 20.9486 24.0703 20.8301C24.0521 21.2129 24.0293 21.6048 24.002 22.0059L23.8516 24.1387C23.1589 24.6126 22.0378 24.9408 20.4883 25.123C20.0326 25.1777 19.5951 25.2051 19.1758 25.2051C16.8607 25.2051 14.9466 24.5306 13.4336 23.1816C11.9115 21.8236 11.1504 20.0872 11.1504 17.9727ZM21.5957 16.25C21.222 14.2904 20.1556 13.3105 18.3965 13.3105C17.0111 13.3105 15.9583 13.9076 15.2383 15.1016C15.0195 15.4661 14.8509 15.8672 14.7324 16.3047C14.9512 16.3138 15.2201 16.3229 15.5391 16.332H16.5508C16.888 16.3411 17.2116 16.3457 17.5215 16.3457H18.3008C18.7656 16.3457 19.2214 16.3411 19.668 16.332L20.7891 16.291C21.0898 16.2819 21.3587 16.2682 21.5957 16.25ZM31.6172 14.4727C31.6172 14.6458 31.6172 14.974 31.6172 15.457C31.6172 15.931 31.64 16.332 31.6855 16.6602H27.6934C27.7754 16.1042 27.8164 15.612 27.8164 15.1836C27.8164 14.7552 27.8118 14.4635 27.8027 14.3086C27.8027 14.1445 27.7982 13.985 27.7891 13.8301C27.7799 13.6751 27.7708 13.5293 27.7617 13.3926L27.7207 13.0508H31.6855C31.6582 13.3971 31.6445 13.6797 31.6445 13.8984L31.6172 14.4727ZM31.6172 22.8125C31.6172 22.9857 31.6172 23.3138 31.6172 23.7969C31.6172 24.2708 31.64 24.6719 31.6855 25H27.6934C27.7754 24.444 27.8164 23.9518 27.8164 23.5234C27.8164 23.0951 27.8118 22.8034 27.8027 22.6484C27.8027 22.4844 27.7982 22.3249 27.7891 22.1699C27.7799 22.015 27.7708 21.8691 27.7617 21.7324L27.7207 21.3906H31.6855C31.6582 21.737 31.6445 22.0195 31.6445 22.2383L31.6172 22.8125ZM42.0078 12.2852C41.9349 12.5495 41.8984 13.2148 41.8984 14.2812V15.4844H38.6035V25H35.2539V15.4844H33.2441C33.2533 14.8828 33.2624 14.3496 33.2715 13.8848L33.2988 12.4219V12.2852H35.2539V11.4375C35.2539 9.25 35.7643 7.49089 36.7852 6.16016C37.8516 4.78385 39.319 4.0957 41.1875 4.0957C41.9805 4.0957 42.5046 4.15039 42.7598 4.25977L42.7734 7.69141L42.7461 7.75977C42.3997 7.41341 41.9577 7.24023 41.4199 7.24023C40.8913 7.24023 40.4629 7.33138 40.1348 7.51367C39.8066 7.68685 39.5286 7.9375 39.3008 8.26562C38.8359 8.92188 38.6035 9.81966 38.6035 10.959V12.2852H42.0078Z" :fill "#2A3947"}]
      [:path {:d "M42 24.8796C42.6981 23.7888 43.4267 22.829 44.1859 22L47 24.212C46.6248 24.6745 46.2757 25.1283 45.9529 25.5733C45.9529 25.5733 45.6169 26.0489 44.945 27L42 24.8796Z" :fill "#DC8C89"}]]]]])

(defn minimize-menu [e]
  (>evt [:toggle-menu-to false]))

(defn header-menu-btn []
  [:div.block.md:hidden
   [:button.flex.items-center.px-3.py-2.text-primary {:on-click (fn [e] (.preventDefault e) (>evt [:toggle-menu]))}
    [:svg.fill-current.h-3.w-3 {:viewBox "0 0 20 20" :xmlns "http://www.w3.org/2000/svg"} [:title "Menu"] [:path {:d "M0 3h20v2H0V3zm0 6h20v2H0V9zm0 6h20v2H0v-2z"}]]]])

(defn header-links []
  [:div.w-full.flex-grow.md:flex.md:items-center.md:w-auto {:class (if (<sub [:menu-expanded]) "block" "hidden")}
   [:div.text-sm.md:flex-grow.font-sans
    [:a.block.mt-4.md:inline-block.md:mt-0.text-primary.hover:text-accent.mr-4.no-underline {:href "/#/" :on-click minimize-menu} "Home"]
    [:a.block.mt-4.mb-4.md:mb-0.md:inline-block.md:mt-0.text-primary.hover:text-accent.no-underline {:href "/#/blog" :on-click minimize-menu} "Blog"]]])

(defn header []
  [:nav.max.w-full.bg-white.border-b.border-gray-200.flex.justify-between.fixed.z-10.top-0
   [:div.w-full.flex.items-center.justify-between.flex-wrap.flex-shrink-0.pl-4.pr-4
    [header-icon]
    [header-menu-btn]
    [header-links]]])

(defn main-view []
  (let [current-route (<sub [:current-route])]
    [:div
     [header]
     [:div.w-full.mx-auto.px-6.pt-20
      (when current-route
        [(-> current-route :data :view)])]
     ]))

