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
* integers: a number without a fractional component（小数部分）.
