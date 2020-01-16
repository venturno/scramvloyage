(ns scramvloyage.core-test
  (:require [clojure.test :refer :all]
            [scramvloyage.scramble :refer [scramble?] :as scramble]
            [clojure.spec.alpha :as s]))

;; repl tests
(comment
  (require '[taoensso.tufte :as tufte])
  (tufte/add-basic-println-handler! {})
  (tufte/profile {}
                 (tufte/p :v1 (v1 "cedewaraaossoqqyt" "codewars"))
                 (tufte/p :v1 (v2 "cedewaraaossoqqyt" "codewars"))
                 (tufte/p :v1 (v3 "cedewaraaossoqqyt" "codewars"))))

(deftest scramble-function
  (testing "spec validation"
    (are [value expected] (= (s/valid? ::scramble/scramble-def value) expected)
      "rekqodlw"  true
      "world"     true
      "steak"     true
      "aa.aaa"    false
      "AAaAaA"    false
      "abbacD!%"  false
      "d"         true
      ">"         false
      ""          false
      nil         false
      {:a 1 :b 2} false))
  (testing "results with expected examples"
    (is (= (scramble? "rekqodlw" "world") true))
    (is (= (scramble? "cedewaraaossoqqyt" "codewars") true))
    (is (= (scramble? "katas" "steak") false))))

