(defproject com.twitter/meat-locker "0.2.0"
  :source-path "src/clj"
  :java-source-path "src/jvm"
  :description "Grab bag of utilities for Cascading DSLs."
  :repositories {"conjars" "http://conjars.org/repo/"}
  :dependencies [[com.esotericsoftware.kryo/kryo "2.02"]]
  :dev-dependencies [[midje "1.3.1" :exclusions [org.clojure/clojure]]
                     [org.clojure/clojure "1.2.1"]
                     [org.apache.thrift/libthrift "0.6.1"]
                     [com.google.protobuf/protobuf-java "2.4.0a"]
                     [org.apache.hadoop/hadoop-core "0.20.2-dev"]
                     [cascading/cascading-hadoop "2.0.0-wip-234"
                      :exclusions [org.codehaus.janino/janino
                                   org.apache.hadoop/hadoop-core]]])
