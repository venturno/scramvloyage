(ns scramvloyage.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [scramvloyage.core-test]
   [scramvloyage.common-test]))

(enable-console-print!)

(doo-tests 'scramvloyage.core-test
           'scramvloyage.common-test)
