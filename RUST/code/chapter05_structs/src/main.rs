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
            width:i32;
            height:i32;
        }

        println!("The area of the rectangle is {} square pixels", area(&Rectangle{
            width:10,height:20
        }) ); 


        fn area(rec:&Rectangle) -> i32{
            rec.width * rec.height
        }
    }
}
