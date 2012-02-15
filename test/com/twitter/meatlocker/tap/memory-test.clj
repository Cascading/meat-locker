(ns com.twitter.meatlocker.tap.memory-test
  (:use cascalog.api
        [cascalog.workflow :only (fields)])
  (:require [clojure.string :as s])
  (:import [cascalog Util]
           [java.util ArrayList]
           [com.twitter.meatlocker.tap MemorySourceTap]
           [cascading.tuple Fields]
           [cascading.flow.hadoop HadoopFlowProcess]
           [org.apache.hadoop.mapred JobConf]))

(def ^:dynamic *default-conf* {})

(def defaults
  {"io.serializations"
   (s/join "," ["org.apache.hadoop.io.serializer.WritableSerialization"
                "cascading.tuple.hadoop.BytesSerialization"
                "cascading.tuple.hadoop.TupleSerialization"])})

(def mk-props
  (partial merge defaults))

(defn job-conf
  "Returns a JobConf instance, optionally augmented by the supplied
   property map."
  ([] (job-conf *default-conf*))
  ([prop-map]
     (let [conf (JobConf.)]
       (doseq [[k v] (mk-props prop-map)]
         (.set conf k v))
       conf)))

(defn tuple-seq
  "Returns all tuples in the supplied cascading tap as a Clojure
  sequence."
  [tap]
  (with-open [it (-> (HadoopFlowProcess. (job-conf))
                     (.openTapForRead tap))]
    (doall (for [wrapper (iterator-seq it)]
             (into [] (.getTuple wrapper))))))

(defn memory-tap
  ([tuples] (memory-tap Fields/ALL tuples))
  ([fields-in tuple-seq]
     (let [tuples (->> tuple-seq
                       (clojure.core/map #(Util/coerceToTuple %))
                       (ArrayList.))]
       (MemorySourceTap. tuples (fields fields-in)))))
