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

##### Floating-Point Types

* Two primitive types for *floating-point*  numbers: `f32` and `f64`. The default type is `f64`.

##### Numeric Operations

* addition
* subtraction
* multiplication
* division: Integer division rounds down to the nearest integer.
* remainder: `%`

##### The Boolean Type

​	The Boolean type in Rust is specified using `bool`.

##### The Character Type

​	`char` type is four bytes in size and represent a Unicode scalar value.

#### Compound Types

##### Tuple

* Tuples have a fixed length: once declared, they cannot grow or shrink in size.

* Create a tuple by writing a comma-separated list of values inside parentheses.

  ```rust
   let tup: (i32, bool, char) = (22, true, '哈');
  ```

* We can use *pattern matching* to destructure a tuple value.

  ```rust
  let (x, y, z) = tup;
  ```

* Using a period`.` followed by the index of the value we want to access.

* `unit`: the tuple without any values. `()`

  Expressions implicitly return the unit value if they don't return any other value.

##### Array Type

* every element of an array must have the same type.

* Arrays in Rust have a fixed length.

* We write values in an array as a comma-separated list inside square brackets.

* You write an array's type using square brackets with the type of each element, a semicolon, and then the number of elements in the array.

  ```rust
  let a: [i32; 3] = [3, 4, 6]; // [type; length]
  ```

* initialize an array to contain the same value for each element by specifying the initial value, followed by a semicolon, and then the length of the array in square brackets.

  ```rust
  let a = [3; 5]; // let a = [3, 3, 3, 3, 3] in a more concise way 
  ```

* Accessing array elements: 

  * using index: `a[0]`

### 3.3 Functions

* Rust doesn't care where you define your functions, only that they're defined somewhere in a scope that can be seen by the caller.

* In function signatures, you must declare the type of each parameter. 

  ```rust
  fn another_function(num: i32, c: char) {
      println!("this is another function!{num},{c}");
  }
  ```

  
