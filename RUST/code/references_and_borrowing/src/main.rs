fn main() {
    {
        let s1 = String::from("hello");
        let len = calculate_length(&s1);
        println!("len is: {len}");
        println!("{s1}");

        fn calculate_length(s: &String) -> usize {
            s.len()
        }
    }
}
