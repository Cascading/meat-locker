(defproject meat-locker "0.1.3-SNAPSHOT"
  :source-path "src/clj"
  :java-source-path "src/jvm"
  :description "Grab bag of utilities for Cascading DSLs."
  :repositories {"conjars" "http://conjars.org/repo/"}
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [com.googlecode/kryo "1.04"]]
  :dev-dependencies [[org.clojure/clojure "1.3.0"]
                     [org.apache.thrift/libthrift "0.6.1"]
                     [com.google.protobuf/protobuf-java "2.4.0a"]
                     [org.apache.hadoop/hadoop-core "0.20.2-dev"]
                     [cascading/cascading-hadoop "2.0.0-wip-184"
                      :exclusions [org.codehaus.janino/janino
                                   org.apache.hadoop/hadoop-core]]])
