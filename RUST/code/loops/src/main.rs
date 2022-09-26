fn main() {
    println!("Hello, world!");

    let mut counter = 0;
    let result = loop {
        counter += 1;
        if counter == 10 {
            break counter * 2;
        }
    };
    println!("The result is {result}");

    let a = [10, 20, 3, 40, 5];
    for element in a {
        print!("{element}\t");
    }

    println!();
    for element in (7..10).rev() {
        print!("{element}\t");
    }
}
