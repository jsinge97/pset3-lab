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

