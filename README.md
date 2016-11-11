# PSET 3 Lab: Macros!

### Problem 1
Alter the syntax of your language! Implement `unless`.

Has the same semantics as `if`, but executes the first branch if the predicate
is false and the second branch if the predicate is true.

### Problem 2
Implement the macro `from`. The macro binds a symbol and can perform the
operations `where`, `orderby`, and `select` over the data it queries.

See the test case under `test/pset3/core_test.clj` for more details.

### Problem 3
Write `with-file`. It takes a filename, and binds it to the symbol `file`.
Within the macro body, `file` refers to the `java.io.File` object.

See the test case under `test/pset3/core_test.clj` for more details.

### Problem 4
What's wrong with this macro?

```clojure
(defmacro square [n]
  `(* ~n ~n))
```

Give us a failing case and write a corrected version of the macro!

### Problem 5: More debugging

Consider the following numeric if macro, which executes `pos`, `zero`, or `neg`, base on the sign of `val`.
```clojure
(defmacro nif [val pos zero neg]
  `(let [~'res ~val]
     (cond (pos? ~'res) ~pos
           (zero? ~'res) ~zero
           :else ~neg)))
```

The fact below holds true as is:
```clojure
(nif -1
     "positive"
     "zero"
     "negative") => "negative"
```

However, this next fact doesn't. Identify the bug and fix it. 
```
 (let [res (java.util.Scanner. (java.io.FileInputStream. "project.clj"))]
   (do (nif 0
            "positive"
            (prn (.nextLine res))
            (prn "negative"))
       (.close res))) => nil
```

### Bonus: Monad comprehension

The construct of *monads* play a central role in the Haskell programming
language, where they underpin the IO system and many other parts of the
language.  At a high level, a monad allows for *composable* computation
descriptions.  For some helpful resources, see

- Haskell wiki article: https://wiki.haskell.org/Monad
- Clojure-focused introduction: https://github.com/khinsen/monads-in-clojure/blob/master/PART1.md


Consider the following list monad:
```
(def list-monad
  {:return (fn [v] [v])
   :bind (fn [mv f]
           (if (seq mv)
             (apply concat (map f mv))
             []))})
```

Using the above implementation, the fact below holds true:

```clojure
(let [bind (:bind list-monad)
      return (:return list-monad)]
  (-> [1 2]
      (bind (fn [a]
              (-> [a (- a)]
                  (bind (fn [b]
                          (return (* 3 b))))))))) => '(3 -3 6 -6)
```

Now, write a macro, `domonad`, that permits a nicer monad comprehension syntax
as follows:

```clojure
(domonad list-monad
  [a [1 2]
   b [a, (- a)]]
   (* 3 b)) => '(3 -3 6 -6)
```

Source: Leonardo Borges's Macro Workshop in Clojure

