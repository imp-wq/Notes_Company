fn main() {
    let mut s = String::from("hello");
    println!("{s}");

    s.push_str(",world!");
    println!("{s}");

    {
        let s1 = String::from("hello");
        let s2 = s1;
        // println!("{s1}"); cause an error
    }

    {
        let s1 = String::from("hello");
        let s2 = s1.clone();
        println!("{s1},{s2}");
    }

    {
        let x = 5;
        let y = x;
        println!("x={},y={}", x, y);
    }

    {
        let s = String::from("hello");

        takes_ownership(s);

        let x = 5;
        makes_copy(x);

        // println!("{s}");
        println!("{x}");

        fn takes_ownership(some_string: String) {
            println!("{some_string}");
        }

        fn makes_copy(some_integer: i32) {
            println!("{}", some_integer);
        }
    }
}
