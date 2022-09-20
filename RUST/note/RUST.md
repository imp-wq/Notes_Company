# RUST

## cargo

* 使用cargo创建新项目：`cargo new 项目名`
* cargo's configuration format: TOML, Tom's Obvious, Minimal Language. 
* build: `cargo build`, results of the build生成在target/debug/目录下。
* release: `cargo build --release`, compile with optimizations, create in `target/release`. 
* run: `cargo run`. 
* check: `cargo check`, quickly checks code to make sure it compiles but doesn't produce a executable. 
* doc: `cargo doc --open`, will build documentation provided by all of dependencies locally.

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
  * expect method:
    *  If this instance of `Result` is an `Ok` value, `expect` will take the return value that `Ok` is holding(`Ok`对应的值) and return just that value to you so you can use it.
    * if  `Result` is an `Err` variant, the `expect` call will crash the game and print the given message.

* `println!` placeholders: use placeholder `{} ` to hold a value in place.

  ```rust
  println!("x={x},y={y}");
  println!("x={},y={}", x, y);
  ```

### Generate a secret number

安装rand crate

* crate: a collection of Rust source code files.

* add rand crate as a dependency: 

  >Cargo’s coordination of external crates is where Cargo really shines.

  * The number `0.8.3` is actually shorthand for `^0.8.3`, which means any version that is at least `0.8.3` but below `0.9.0`.
  * Semantic Version: aka `SemVer`, Cargo considers these versions to have public APIs compatible with version `0.8.3`.

* When we include an external dependency, Cargo fetches the latest versions of everything that dependency needs from the *registry*, which is a copy of data from `Crate.io`.

  `Crate.io `应该就跟npm对于js类似。

* After updating the registry, Cargo downloads crates. Cargo also grabbed other crates that `rand` depends on to work. 

* `Cargo.lock` file: 类似`package.lock.json.`，用于锁定版本的。

使用rand crate

```rust
let secret_number = rand::thread_rng().gen_range(1..=100);
```

* `use rand::Rng`: The `Rng` trait defines methods that random number generators implement, this trait must be in scope to use those methods.
* trait `rand::thread_rng`: This function gives the random number generator.
* `gen_range`: method on the random number generator, takes **a range expression** as an argument and generates a random number in the range.
  * range expression: `start...=end` , is inclusive on the lower and upper bounds([start, end]，包含上下边界)

### Comparing the Guess to the Secret Number

```rust
 match guess.cmp(&secret_number) {
     std::cmp::Ordering::Greater => println!("Too big!"),
     std::cmp::Ordering::Less => println!("Too small!"),
     std::cmp::Ordering::Equal => println!("You win!"),
}
```

* enum type `std::cmp::Ordering`:

  * varients: `Less`, `Greater`, `Equals`	Three outcomes that are possible when you compare two values.

* `cmp` method: compares to values, can be called on anything that can be compared. 

  * take a **reference** as an argument.
  * return a variant of `Ordering` enum.
  * use `match expression` to decide what to do next based on which varient of `Ordering` was returned.

* `match` expression:  made up of `arms`.

  Rust的分支判断语句

  * arm: consists of a *pattern*, and the code should be run if the value given to `match` fits the pattern.

* type system: 对于整数类型，有`i32`,`u32`,`i64`,`u64`等多种类型，默认为`i32`。



convert String into a real number type:

```rust
let guess: u32 = guess.trim().parse().expect("Please type a number!");
```

* Shadowing: let us reuse the variable name, often used when you want to convert a value from one type to another type.

* `trim` method on strings: eliminate the newline charactor of the String.

  `read_line`方法在输入时会读入末尾的换行符。因此需要trim方法去掉该换行符。

* `parse` method on a strings: converts a string to another type. Tell Rust the target type by using `let guess: u32`.

  * If characters can't logically be converted into numbers, `parse` method will cause errors. 
  * Because it might fail, the `parse` method returns a `Result` type.
  * Treat this Result by using the `expect` method.

### Allowing Multiple Gusses with Looping

* `loop` keyword: creates an infinite loop.



