/*
 * @Author: wuzhenyang
 * @Date: 2022-09-29 12:57:12
 * @LastEditors: wuzhenyang
 * @LastEditTime: 2022-11-10 17:04:09
 * @FilePath: \code\references_and_borrowing\src\main.rs
 * @Description:
 */
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

    {
        println!("---reference and borrowing---");
        let mut s = String::from("hello");
        change(&mut s);
        println!("{s}");
        fn change(some_string: &mut String) {
            some_string.push_str(", world");
        }
    }
    {
        let s = "hello";
        let r1 = &mut s;
        let r2 = &mut s;
        println!("{},{}", r1, r2);
    }

    {
        let reference_to_nothing = dangle();
        fn dangle() -> &String {
            let s = String::from("hello");
            &s;
        }
    }
}
