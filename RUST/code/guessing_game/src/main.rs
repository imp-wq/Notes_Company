use rand::Rng;
use std::io;

fn main() {
    println!("Guess the number!");
    let secret_number = rand::thread_rng().gen_range(1..=100);

    loop {
        println!("Please input your guess:");

        println!("The secret number is:{secret_number}");

        let mut guess = String::new();
        io::stdin()
            .read_line(&mut guess)
            .expect("Failed to read line");

        let guess: u32 = guess.trim().parse().expect("Please type a number!");
        println!("You guessed: {guess}");

        match guess.cmp(&secret_number) {
            std::cmp::Ordering::Greater => println!("Too big!"),
            std::cmp::Ordering::Less => println!("Too small!"),
            std::cmp::Ordering::Equal => {
                println!("You win!");
                break;
            }
        }
    }

    // let x = 5;
    // let y = 10;
    // println!("x={x},y={y}");
    // println!("x={},y={}", x, y);
}