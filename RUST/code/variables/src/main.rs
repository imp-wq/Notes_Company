fn main() {
    let mut x = 5;
    println!("The value of x is: {x}");
    x = 6;
    println!("The value of x is: {x}");

    println!("-------------shadowing-----------------",);

    let x = 5;
    let x = x + 1;
    {
        // create an inner scope with the curly brackets.
        let x = x * 2;
        println!("The value of x in the inner scope is: {x}");
    }

    // the inner scope is over, the inner shadowing ends.
    println!("The value of x is: {x}");
}
