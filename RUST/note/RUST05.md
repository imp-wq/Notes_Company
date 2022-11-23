# 05 Using Structs to Structure Related Data

## 5.1 Defining and Instantiating Structs

* define a struct:

  ```rust
  struct User {
      username: String,
      password: String,
  }
  ```

* create an instance of the struct:

  ```rust
  let user1=User {
      password:String::from("123qwer"),
      username:String::from("dog")
  };
  ```

* dot notation: we use dot notation to get a specific value from a struct.

  The instance must be mutable, so we can change the value.

  ```rust
  let mut user1 = User {
      password: String::from("123qwer"),
      username: String::from("dog"),
      email: String::from("hahaha@qq.com"),
  };
  user1.email = String::from("hahaha@gmail.com");
  ```

### Tuple Structs

```rust
// tuple structs
println!("---tuple structs---");
struct Color(i32, i32, i32);
let black = Color(0, 0, 0);
println!("index 1 of black: {}", black.1);
```

* tuple instances are similar to tuples in that we can destructure them into their individual pieces.
* we can use a `.` followed by the index to access an individual value.

### Unit-like Structs

* unit-like structs don't have any fields, and they behave similarly to `()`, the unit type.
* it's useful in trait.

## 5.2 An Example Program Using Structs

```rust
println!(
    "The area of the rectangle is {} square pixels",
    area(&Rectangle {
        width: 10,
        height: 20
    })
);

// the parameter is an immutable borrow
fn area(rec: &Rectangle) -> i32 {
    rec.width * rec.height
}
```

### Adding Useful Functionality with Derived Traits

* `{?:}` & `#[derived(Debug)]`
* `dbg!` macro

## 5.3 Method Syntax

### Defining Methods

* We define methods in an `impl` (implementation) block for the struct.

* Methods must have a parameter named `self` of type `Self` for their first parameter, and `&self` is short for `self:Self`.

  Note that we still need to use `&` in front of the `self` shorthand to indicate this method do not take the ownership of `self`.

### Methods with More Parameters

### Associated Functions



## Some questions

* what's the differences between string literal and String ?
* What will happen if we declare a function with an ordinary parameter but give it a reference when calling it ?

## Language

* This way, `main` **retains** its ownerships ...
* The `area` function **accesses** the `width` and `height` fields of the `Rectangle` instance.

* abbreviate, ergonomic
