(defproject
  downnotifier
  "0.1.0-SNAPSHOT"
  :description
  "FIXME: write description"
  :ring
  {:handler downnotifier.handler/app,
   :init downnotifier.handler/init,
   :destroy downnotifier.handler/destroy}
  :plugins
  [[lein-ring "0.8.10"] [lein-environ "0.5.0"]]
  :url
  "http://example.com/FIXME"
  :profiles
  {:uberjar {:aot :all},
   :production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}},
   :dev
   {:dependencies
    [[ring-mock "0.1.5"]
     [ring/ring-devel "1.3.0"]
     [pjstadig/humane-test-output "0.6.0"]],
    :injections
    [(require 'pjstadig.humane-test-output)
     (pjstadig.humane-test-output/activate!)],
    :env {:dev true}}}
  :jvm-opts
  ["-server"]
  :dependencies
  [[selmer "0.6.9"]
   [log4j
    "1.2.17"
    :exclusions
    [javax.mail/mail
     javax.jms/jms
     com.sun.jdmk/jmxtools
     com.sun.jmx/jmxri]]
   [com.taoensso/timbre "3.2.1"]
   [markdown-clj "0.9.47"]
   [com.h2database/h2 "1.4.180"]
   [noir-exception "0.2.2"]
   [im.chit/cronj "1.0.1"]
   [environ "0.5.0"]
   [korma "0.3.3"]
   [org.clojure/clojure "1.6.0"]
   [ring-server "0.3.1"]
   [com.taoensso/tower "2.0.2"]
   [lib-noir "0.8.4"]
   [http-kit "2.1.16"]
   [digest "1.4.4"]
   [clj-time "0.8.0"]]
  :repl-options
  {:init-ns downnotifier.repl}
  :min-lein-version "2.0.0")
