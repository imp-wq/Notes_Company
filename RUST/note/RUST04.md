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
* When functions have references as parameters instead of the actual values, we won't need to return the values in order to give back ownership, because we never had ownership.
* We call the action of creating a reference *borrowing*.
* References are immutable by default, so we're not allowed to modify something we have a reference to.

### Mutable References

* we change `s` to be `mut`.
* we create a mutable reference with `&mut s`  .
* function accept a mutable reference with `some_string: &mut String`.
* One big restriction of mutable references: if you have a mutable reference to a value, you can have no other references to that value.
* The benefit of having this restriction is that Rust can prevent data races at compile time.
* Rust enforces a similar rule for combining mutable and immutable references. That's means we also cannot have a mutable reference while we have an immutable one to the same value.

```rust
println!("---reference and borrowing---");
let mut s = String::from("hello");
change(&mut s);
println!("{s}");
fn change(some_string: &mut String) {
    some_string.push_str(", world");
}
```

* A reference's scope starts from where it is introduced and continues through the last time that reference is used.
* Non-Lexical Lifetimes: NLL, the ability of the compiler to tell that a reference is no longer being used at a point before the end of the scope.

#### data race

* A data race happens when these three behaviors occur:
  * Two or three pointers access the same data at the same time.
  * At least one of the pointers is being used to write to the data.
  * There is no mechanism being used to synchronize access to the data.
* We can use curly brackets to create a new scope, allowing for multiple mutable references, just not simultaneous ones.

### Dangle References

>In languages with pointers, it’s easy to erroneously create a *dangling pointer*--a pointer that references a location in memory that may have been given to someone else--by freeing some memory while preserving a pointer to that memory. 

* We shouldn't return a reference to a `String` in functions.
* `s` is created inside `dangle`, when the code of `dangle` is finished, `s` will be deallocated, `&s` would be pointing to an invalid `String`.

```rust
let reference_to_nothing = dangle();
fn dangle() -> &String {
    let s = String::from("hello");
    &s; // error! 
    // this function's return type contains a borrowed value, but there is no value for it to be borrowed from
}
```



