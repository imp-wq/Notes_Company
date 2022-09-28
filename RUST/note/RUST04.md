# 04 Understanding Ownership

​	Ownership enables Rust to make memory safety guarantees without needing a garbage collector.

* *Ownership* is a set of rules that governs how a Rust program manages memory.

## Ownership rules

* Each value in Rust has an *Owner*.
* There can only be one owner at a time.
* When the owner goes out of scope, the value will be dropped.

## The String Type

* `String` type and string literal.

  * String literal: immutable.

  * `String`: `String`manages data allocated on the heap.

    * `String` can be created from a string literal using the `from` function:

      ```rust
       let s = String::from("hello");
      ```

* We need a way of returning this memory to the allocator when we're done with our `String`.

  * When a variable goes out of scope, Rust calls a special function `drop` to return the memory.

* A `String` is made up of three parts:

  * a pointer to the memory that holds the contents of the string
  * a length: how much memory is currently using
  * a capacity: total amount of memory, received from the allocator.

  <img src=".\note images\parts of a string.png"  />

## Move

```rust
// move
let s1 = String::from("hello");
let s2 = s1;
```

* Rust considers `s1` as no longer valid in this case.

* Because Rust invalidates the first variable, it's not a shallow copy, it's known as a *move*.

  We would say `s1` was *moved* into `s2`.

* Rust will never automatically "deep" copies of your data.

## Clone

​	*Clone* is a common method to deeply copy the heap data, not just the stack data.

## Copy

