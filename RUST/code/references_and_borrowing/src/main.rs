/*
 * @Author: wuzhenyang
 * @Date: 2022-09-29 12:57:12
 * @LastEditors: wuzhenyang
 * @LastEditTime: 2022-11-11 17:14:40
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
        // let s = "hello";
        // let r1 = &mut s;
        // let r2 = &mut s; error!
        // println!("{},{}", r1, r2);
    }

    {
        // let reference_to_nothing = dangle();
        // fn dangle() -> &String {
        //     let s = String::from("hello");
        //     &s;
        // }
    }

    // 4.3 The Slice Type
    {
        println!("---The slice type---");
        let mut s = String::from("hello world");
        let word = first_word(&s);
        s.clear();
        println!("{}", word);

        /**
         *  this function takes a string of words separated by space and returns the first word it finds in that string.
         */
        fn first_word(s: &String) -> usize {
            // we convert our `String` to an array of bytes.
            let bytes = s.as_bytes();

            // we create an iterator over the array using the `iter` method.
            // `enumerate` wraps the result of `iter` and returns each element as part of a tuple instead.
            // we can use patterns to destructure the tuple.
            for (index, &item) in bytes.iter().enumerate() {
                if item == b' ' {
                    return index;
                }
            }
            s.len()
        }
        {
            let s = String::from("hello, world!");
            // let hello = &s[0..5];
            let hello = &s[..5];

            // let world = &s[7..s.len()];
            let world = &s[7..];
            println!("s is: {}; hello is: {}; world is: {}", s, hello, world);
        }
        {
            println!("---rewrite the first_word to return a slice---");
            let mut s = String::from("hello,world");
            let word = first_word(&s);
            // s.clear(); error! mutable borrow occurs here!
            println!("the first word is: {word}");

            fn first_word(s: &str) -> &str {
                let bytes = s.as_bytes();

                for (index, &item) in bytes.iter().enumerate() {
                    if item == b' ' {
                        return &s[..index];
                    }
                }
                &s[..]
            }
        }
        {}
    }
}
