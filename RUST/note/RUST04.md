# 04 Understanding Ownership

​	Ownership enables Rust to make memory safety guarantees without needing a garbage collector.

* *Ownership* is a set of rules that governs how a Rust program manages memory.

## 4.1 What is Ownership?

### Ownership rules

* Each value in Rust has an *Owner*.
* There can only be one owner at a time.
* When the owner goes out of scope, the value will be dropped.

### The String Type

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

### Move

```rust
// move
let s1 = String::from("hello");
let s2 = s1;
```

* Rust considers `s1` as no longer valid in this case.

* Because Rust invalidates the first variable, it's not a shallow copy, it's known as a *move*.

  We would say `s1` was *moved* into `s2`.

* Rust will never automatically "deep" copies of your data.

### Clone

​	*Clone* is a common method to deeply copy the heap data, not just the stack data.

### Copy

* `Copy` trait: if a type implements the `Copy` trait, variables that use it do not move, but rather are trivially copied, making them still valid after assignment to another variable.

  * as a general rule, any group of simple scalar values can implement `Copy`.

* 对于基本数据类型scalar types，赋值操作执行copy，原变量可以使用。

  对于引用数据类型compound types, 赋值操作执行move，原变量所有权转移，不可再使用。

### Ownership and Functions

* The mechanics of passing a value to a function are similar to those when assigning a value to a variable.

### Return Values and Scope

* Returning values can also transfer ownership.
* Rust does let us return multiple values using a tuple.

## 4.2 References and Borrowing

* reference
* ampersand `&`: ampersand represent *references*, they allow you to refer to some value without taking ownership of it.
* 
