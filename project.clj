(defproject meat-locker "0.1.1"
  :source-path "src/clj"
  :java-source-path "src/jvm"
  :description "Grab bag of Kryo Serializations."
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [com.googlecode/kryo "1.04"]]
  :dev-dependencies [[org.clojure/clojure "1.3.0"]
                     [org.apache.thrift/libthrift "0.6.1"]
                     [com.google.protobuf/protobuf-java "2.4.0a"]])
