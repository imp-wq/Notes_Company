# 05 Using Structs to Structure Related Data

### 5.1 Defining and Instantiating Structs

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



