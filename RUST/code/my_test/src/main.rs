use std::cmp::Ordering;
// use std::io;

fn main() {
    // let mut str = String::new();
    // io::stdin().read_line(&mut str).expect("failt to read line");
    let num = 10;
    match num.cmp(&10) {
        Ordering::Less => println!("less"),
        Ordering::Greater => println!("Greater"),
        Ordering::Equal => println!("Equal"),
    }
}
