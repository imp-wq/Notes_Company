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
}
