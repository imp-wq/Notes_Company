# RUST

## cargo

* 使用cargo创建新项目：`cargo new 项目名`
* cargo's configuration format: TOML, Tom's Obvious, Minimal Language. 
* build: `cargo build`, results of the build生成在target/debug/目录下。
* release: `cargo build --release`, compile with optimizations, create in `target/release`. 
* run: `cargo run`. 
* check: `cargo check`, quickly checks code to make sure it compiles but doesn't produce a executable. 

## guess game

* `std::io` library: io library, comes from standard library. 

  `use`: bring library into scope.

* fn: declares a new function. 

* let: to create a variable.

* mut: variables are immutable by default in Rust, to make a variable mutable, we add `mut` before the variable name.

* `String::new()`: a **function** that returns a new instance of a String.

  An *associated function* is a function that’s implemented on a type

* &: indicates that this argument is a *reference*, which gives you a way to let multiple parts of your code access one piece of data without needing to copy that data into memory multiple times. 

* read_line function: puts whatever the user enters into the string we pass to it, and returns  `Result` value.

* Result value: an enumeration. variant: each possible state of an enumeration.

  * variants are `Ok` and `Err`.
  * expect method: If this instance of `Result` is an `Ok` value, `expect` will take the return value that `Ok` is holding(`Ok`对应的值) and return just that value to you so you can use it.

* `println!` placeholders: use placeholder `{} ` to hold a value in place.

  ```rust
  println!("x={x},y={y}");
  println!("x={},y={}", x, y);
  ```

### Generate a secret number

* crate: a collection of Rust source code files.

* add rand crate as a dependency: 

  >Cargo’s coordination of external crates is where Cargo really shines.

  * The number `0.8.3` is actually shorthand for `^0.8.3`, which means any version that is at least `0.8.3` but below `0.9.0`.
  * Semantic Version: aka `SemVer`, Cargo considers these versions to have public APIs compatible with version `0.8.3`.

* When we include an external dependency, Cargo fetches the latest versions of everything that dependency needs from the *registry*, which is a copy of data from `Crate.io`.

  `Crate.io `应该就跟npm对于js类似。

* After updating the registry, Cargo downloads crates. Cargo also grabbed other crates that `rand` depends on to work. 

* `Cargo.lock` file: 类似`package.lock.json.`，用于锁定版本的。