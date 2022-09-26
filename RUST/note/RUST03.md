## 3. Common Programming Concepts

### 3.1 Variables and Mutability

* by default variables are immutable.

#### Constants

​	`Constants` are not allowed to change, but there are differences between constants and immutable variables.

* declare constants using the `const` keyword.
* the type of value must be annotated.
* may be set only a constant expression, not the result of a value that could only be computed at runtime.
* naming convention for constants: use all uppercase with underscores between words.
* Naming hardcoded  values used throughout program as constants.

#### Shadowing

* You can declare a new variable with the same name as previous variable.

* In effect, the second variable overshadows the first, until either it itself is shadowed or the scope ends.

  ```rust
  let x = 5;
  let x = x + 1;
  {
      // create an inner scope with the curly brackets.
      let x = x * 2;
      println!("The value of x in the inner scope is: {x}");
  }
  
  // the inner scope is over, the inner shadowing ends.
  println!("The value of x is: {x}");
  ```

* In shadowing we create a new variable by using the `let` keyword again, so we can change the type of the value but reuse the same name.

  This is different from marking a variable as `mut`.

### 3.2 Date Types

* Rust is a **statically typed** language, which means that it must know the types of all variables at compile time. 

* When many types are possible, we must add a type annotation.

  ```rust
  // There are many numeric types, so we must add a type annotation.
  let guess: u32 = "42".parse().expect("Not a number!");
  ```

#### Scalar Types

​	A scalar type represents a single value.

* four primary scalar types:
  * integers
  * floating-point numbers
  * Booleans
  * characters

##### Integer Types

​	a number without a fractional component（小数部分）.

* Each variant be either signed or unsigned and has an explicit size.

  `i32,u32`

* `isize` and `usize`: depend on the architecture of the computer program is running on. 64 bits if running on a 64-bit architecture and 32-bits if running on a 32-bit architecture.

*  Number literals that can be multiple numeric types allow a type suffix.

* Can also use underscore`_` as a visual separator to make number easier to read, such as`1_000` is same as `1000`.

* *Integer overflow* will cause programs to panic at runtime in debug mode, and cause *complement wrapping* in release mode with `--release` flag.

#### Floating-Point Types

* Two primitive types for *floating-point*  numbers: `f32` and `f64`. The default type is `f64`.

#### Numeric Operations

* addition
* subtraction
* multiplication
* division: Integer division rounds down to the nearest integer.
* remainder: `%`

#### The Boolean Type



---

### 3.3 Functions

#### Statements and Expressions

* Statements: instructions  that perform some action and do not return a value.

  ```rust
  // let statement
  let x = 5;
  ```

* Expressions: expressions evaluate to a resulting value.

  Expression do not include ending semicolons. If you add a semicolon to the end of an expression, you turn it into a statement.

  * calling a function

  * calling a macro

  * creating a new scope block with curly brackets

    ```rust
     let y = {
            let x = 3;
            x + 1
        }; // is a block evaluates to 4
    // y is 4
    ```

#### Functions with Return Values

* We must declare return value's type after an arrow(->).

* The return value of the function is synonymous with the value of the final expression in the block of the body of a function.

  ```rust
  fn getNumber() -> i32 {
      222
  }
  ```

#### Comments

//

### 3.4 Control Flow

#### `if` Expressions

​	An `if` expression allows you to branch  your code depending on conditions.

* Blocks of the code associated with the conditions in `if` expressions are sometimes called arms.
* if condition isn't a `bool`, we'll get an error.
* Because `if` is an expression, we can use it on the right side of a `let` statement to assign the outcome to a variable.
  * This means the values that have the potential to be results from each arm of the `if` must be the same type.
  * If the types are mismatched, we'll get an error.
  * Because variables must have a single type, and Rust needs to know at compile time what type the variable is.

#### Repetition with Loops

* Three kinds of loops: `loop`,`while`, and  `for`.

  * `loop`: the loop keywords tell Rust to execute a block of code over and over again forever or until you explicitly tell it to stop.
  * `while`: conditional loops with while.
  * `for`: looping through a collection.
    * use a `Range` with a `for` loop: generates all numbers in sequence starting from one number and ending before another number.  

* `break`: to tell the program when to stop executing the loop.

  * You can add the value you want returned after the `break` expression you use to stop the loop.

    ```rust
    let mut counter = 0;
    let result = loop {
        counter += 1;
        if counter == 10 {
            break counter * 2;
        }
    };
    println!("The result is {result}");
    ```

* `continue`: to tell the program to skip over any remaining code in this iteration of the loop and go to the next iteration.

* loop labels to disambiguate between multiple loops:

  * loop labels must begin with a single quote.
