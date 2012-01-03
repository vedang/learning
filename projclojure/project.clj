(defproject projclojure "1.0.0-SNAPSHOT"
  :description "A test project to learn clojure"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [org.clojure/data.finger-tree "0.0.1"]
                 [clj-stacktrace "0.2.4"]
                 [ring "1.0.1" :exclusions [clj-stacktrace]]
                 [ring-json-params "0.1.3"]]
  :dev-dependencies [[swank-clojure "1.3.4"]]
  :repositories {"The Buzz Media Maven Repository" "http://maven.thebuzzmedia.com",
                 "Jahia" "http://maven.jahia.org/maven2/"}
  :warn-on-reflection true)
