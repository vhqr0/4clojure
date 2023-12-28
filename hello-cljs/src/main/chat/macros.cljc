(ns chat.macros
  (:require [clojure.core.async :refer [chan sub <! go-loop]]))

(defmacro go-loop-sub [pub key binding & body]
  `(let [ch# (chan)]
     (sub ~pub ~key ch#)
     (go-loop []
       (let [~binding (<! ch#)]
         ~@body)
       (recur))))
