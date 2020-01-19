# scramvloyage

Can the letters "reqkqoheahhlolwrd" be rearranged to form "hello world"?

A voyage in running a letter scramble function in clojure. Scramvloyage.

Project scaffolded using lein chestnut template

## Requirements

leiningen 2.9.1
openjdk-8

## Running

First, compile the javascript with `lein cljsbuild once`.

Then you can `lein run` to start up the server. 

Point the browser at localhost:10555 once it's running.

## Development

Open a terminal and type `lein repl` to start a Clojure REPL
(interactive prompt).

In the REPL, type

```clojure
(go)
(cljs-repl)
```

The call to `(go)` starts the Figwheel server at port 3449, which takes care of
live reloading ClojureScript code and CSS, and the app server at port 10555 
which forwards requests to the http-handler defined. 

Running `(cljs-repl)` starts the Figwheel ClojureScript REPL. Evaluating
expressions here will only work once you've loaded the page, so the browser can
connect to Figwheel.

When you see the line `Successfully compiled "resources/public/app.js" in 21.36
seconds.`, you're ready to go. Browse to `http://localhost:10555` and enjoy.

**Attention: It is not needed to run `lein figwheel` separately. Instead `(go)`
launches Figwheel directly from the REPL**

### Emacs/CIDER

CIDER is able to start both a Clojure and a ClojureScript REPL simultaneously,
so you can interact both with the browser, and with the server. The command to
do this is `M-x cider-jack-in-clojurescript`.

We need to tell CIDER how to start a browser-connected Figwheel REPL though,
otherwise it will use a JavaScript engine provided by the JVM, and you won't be
able to interact with your running app.

Put this in your Emacs configuration (`~/.emacs.d/init.el` or `~/.emacs`)

``` emacs-lisp
(setq cider-cljs-lein-repl
      "(do (user/go)
           (user/cljs-repl))")
```

Now `M-x cider-jack-in-clojurescript` (shortcut: `C-c M-J`, that's a capital
"J", so `Meta-Shift-j`), point your browser at `http://localhost:10555`, and
you're good to go.

## Testing

To run the Clojure tests, use

``` shell
lein test
```

In the scramble tests file, there are some sexps commented to profile the different versions I came up with to test the algorithm. It requires taoensso/tufte in deps to do so.

## License

Copyright © 2020 

Distributed under the Eclipse Public License either version 1.0 

## Chestnut

Created with [Chestnut](http://plexus.github.io/chestnut/) 0.18.0 (40a06fcf).
