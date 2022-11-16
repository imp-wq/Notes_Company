/*
 * @Author: wuzhenyang
 * @Date: 2022-11-15 10:30:50
 * @LastEditors: wuzhenyang
 * @LastEditTime: 2022-11-16 17:40:43
 * @FilePath: \code\chapter05_structs\src\main.rs
 * @Description:
 */
fn main() {
    struct User {
        username: String,
        password: String,
        email: String,
    }

    let mut user1 = User {
        password: String::from("123qwer"),
        username: String::from("dog"),
        email: String::from("hahaha@qq.com"),
    };

    user1.email = String::from("hahaha@gmail.com");
    println!(
        "email: {},username: {}, password: {}",
        user1.email, user1.username, user1.password
    );

    println!("Hello, world!");

    build_user(
        String::from("123@qq.com"),
        String::from("cat"),
        String::from("123456"),
    );

    fn build_user(email: String, username: String, password: String) -> User {
        User {
            email,
            username,
            password,
        }
    }

    {
        // tuple structs
        println!("---tuple structs---");
        struct Color(i32, i32, i32);
        let black = Color(0, 0, 0);
        println!("index 1 of black: {}", black.1);
    }

    {
        // calculate the area of a rectangle
        println!("---calculate the area of a rectangle---");

        struct Rectangle {
            width: i32,
            height: i32,
        }

        println!(
            "The area of the rectangle is {} square pixels",
            area(&Rectangle {
                width: 10,
                height: 20
            })
        );

        // the parameter is an immutable borrow
        fn area(rec: &Rectangle) -> i32 {
            rec.width * rec.height
        }
    }
    {
        // print a struct
        #[derive(Debug)]
        struct Rectangle {
            width: i32,
            height: i32,
        }
        println!("------");
        let rect1 = Rectangle {
            width: 30,
            height: 50,
        };
        println!("{:?}", rect1);
        dbg!(&rect1);
    }

    {
        // method syntax
        println!("---method syntax---");
        #[derive(Debug)]
        struct Rectangle {
            width: u32,
            height: u32,
        }
        impl Rectangle {
            fn area(&self) -> u32 {
                self.width * self.height
            }
        }
    }
}
