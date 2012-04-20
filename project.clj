(defproject com.twitter/meat-locker "0.2.2-SNAPSHOT"
  :source-path "src/clj"
  :java-source-path "src/jvm"
  :description "Serializers for Kryo."
  :repositories {"conjars" "http://conjars.org/repo/"}
  :dependencies [[com.twitter/kryo "2.04"]]
  :dev-dependencies [[midje "1.3.1" :exclusions [org.clojure/clojure]]
                     [org.clojure/clojure "1.2.1"]
                     [org.apache.thrift/libthrift "0.6.1"]
                     [com.google.protobuf/protobuf-java "2.4.0a"]])
