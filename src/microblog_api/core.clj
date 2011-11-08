(ns microblog-api.core)
;(use 'microblog-api.core :reload-all)

(def last-id (atom 0))

(def storage (atom {}))

(defn gen-post-id []
  (swap! last-id inc))

(defn save-post [post]
  (swap! storage (fn [storage]
                   (->
                     storage
                     (update-in [:id-index] assoc (:id post) post)
                     (update-in [:time-index] conj post)
                   )))
  post
  )

(defn create-post [user text]
  (let [post {:post-id (gen-post-id)  :current-time (System/currentTimeMillis) :user user :text text}]
    (save-post post))
)

(defn get-post [post-id]
)

(defn timeline [offset n]
)

;test code
(save-post {:id 1 :text "ass-sock"})
(save-post {:id 2 :text "suck it clojure"})
(save-post {:id 3 :text "1958 called, they want their language back."})
@storage
