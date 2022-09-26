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

    let tup: (i32, bool, char) = (22, true, '哈');
    let (x, y, z) = tup;
    let x = tup.0;
    let y = tup.1;
    println!("{x},{y},{z}");

    let a: [i32; 3] = [3, 4, 6];

    another_function(22, '哈');
}

fn another_function(num: i32, c: char) {
    println!("this is another function!{num},{c}");
}
