(ns bot.bot
  (:require [clojure.string :as s])
  (:import [java.awt Robot Rectangle Toolkit Image Color MouseInfo])
  (:import [javax.swing ImageIcon JOptionPane])
  (:import java.awt.Window)
  (:gen-class))

(def screen (.getScreenSize (Toolkit/getDefaultToolkit)))

(def bot (Robot.))

(defn c [x y]
  (.getPixelColor bot x y))

(defn sc [x y w h]
  (let [r (Rectangle. x y w h)]
    (.createScreenCapture bot r)))

(defn point [] 
  (let [m (MouseInfo/getPointerInfo)]
    (if (nil? m)
      []
      (let [p (.getLocation m)
            x (Math/round (.getX p))
            y (Math/round (.getY p))]
        [x y]))))

; set this to false to stop the shaker.
(def mouse-shaking (atom true))

(defn shake []
  (reset! mouse-shaking true)
  (let [p (point)]
    (if (empty? p) :nix
        (let [[x y] p
              dx (rand-int 1)
              dy (rand-int 1)]
          (.mouseMove bot (+ x dx) (+ y dy)))))
  (if @mouse-shaking 
    (do
      (Thread/sleep (* 1 60 1000))
      (recur))))

(defn -main [& args]
  (shake))

(defn stop [] (reset! mouse-shaking false))

(def test-image (sc 1500 60 700 500))

; simplest image viewer
(defn show [^Image img]
  (let [g (.createGraphics img)
        ii (ImageIcon.)]
    (.setColor g (Color/red))
    (.fillOval g 50 50 50 50)
    (.setImage ii img)
    (JOptionPane/showMessageDialog nil ii)))

; (show test-image)

;; the rest doesn't really work, still.

(def iIcon (ImageIcon.))

(defn showP [^Image img]
  (let [g (.createGraphics img)]
    (.setColor g (Color/red))
    (.fillOval g 50 50 50 50)
    (.setImage iIcon img)
    (JOptionPane/showMessageDialog nil iIcon)))

(defn re [] (.setImage iIcon (sc 1500 60 700 500)))
