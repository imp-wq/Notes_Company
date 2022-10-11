# JDBC

home，mysql root密码：`mysql`。

JDBC，Java DataBase Connectivity, 是用java操作关系型数据库的api。

* java提供JDBC接口。
* MySQL等数据库厂商提供实现类，即数据库驱动，供java调用。
* 从而保证一套JDBC接口，配合不同的数据库驱动，可以操作所有关系型数据库。



* 创建新项目：
  1. idea创建空项目
  2. 在空项目中创建new module，JDBC demo
  3. 在JDBC demo module中创建lib目录，用于存放依赖。
  4. 将mysql驱动jar包放入lib目录，右键add as library。

## 基本使用

```java
// 1.注册驱动，可以省略
Class.forName("com.mysql.jdbc.Driver");

// 2.建立连接，获取连接对象。
// String url = "jdbc:mysql://127.0.0.1:3306/db_demo1?useSSL=false";
// 省略本机ip和端口号3306
String url = "jdbc:mysql:///db_demo1?useSSL=false";
String username = "root";
String password = "mysql";

Connection conn = DriverManager.getConnection(url, username, password);

// 3.定义sql
String sql = "UPDATE account SET money = 2000 WHERE id = 1;";

// 4.获取statement对象
Statement statement = conn.createStatement();

// 5.执行sql，返回值为受影响的行数。
int count = statement.executeUpdate(sql);
System.out.println(count);

// 6.释放资源
statement.close();
conn.close();
```

### DriverManager

驱动管理类

* 作用：
  * 注册驱动
  * 获取数据库连接
* 通过反射注册驱动的操作可省略：驱动类名会保存在`lib\mysql-connector-java-5.1.48.jar\META-INF\services\java.sql.Driver`中，无需手动注册。

* 获取连接：
  * `jdbc:mysql://ip地址（域名）:端口号/数据库名?参数键值对`
  * 协议名：jdbc:mysql
  * ip默认本机，端口号默认3306。
  * useSSL=false，禁用安全连接方式。

### Connection

数据库连接对象

* 作用：

  * 获取执行SQL对象

    * createStatement
    * prepareStatement
    * prepareCall

  * 管理事务

    * setAutoCommit(boolean autoCommit)：设置为true自动提交事务。设置为false手动提交，即通过该方法开启事务。
    * 提交事务：commit()
    * 回滚事务：rollback()
    * 事务的提交和回滚可以配合try-catch进行使用。

    ```sql
    // 1.注册驱动
            // Class.forName("com.mysql.jdbc.Driver");
    
            // 2.建立连接，获取连接对象。
    //        String url = "jdbc:mysql://127.0.0.1:3306/db_demo1?useSSL=false";
            String url = "jdbc:mysql:///db_demo1?useSSL=false";
            String username = "root";
            String password = "mysql";
    
            Connection conn = DriverManager.getConnection(url, username, password);
            // 3.定义sql
            String sql1 = "UPDATE account SET money = 3000 WHERE id = 1;";
            String sql2 = "UPDATE account SET money = 3000 WHERE id = 2;";
    
            // 4.获取statement对象
            Statement statement = conn.createStatement();
    
            // 开启事务
    
            try {
                conn.setAutoCommit(false);
    
                // 5.执行sql，返回值为受影响的行数。
                int count1 = statement.executeUpdate(sql1);
                System.out.println(count1);
    
                // 制造异常
                // int i = 3 / 0;
    
                int count2 = statement.executeUpdate(sql2);
                System.out.println(count2);
    
                // 提交事务
                conn.commit();
            } catch (Exception throwables) {
                // 回滚事务
                conn.rollback();
                throwables.printStackTrace();
            }
    
    
            // 6.释放资源
            statement.close();
            conn.close();
    ```

### Statement

Statement对象用于执行sql语句，根据sql语句不同的类型用不同的方法执行。

* int executeUpdate：执行DM(Manipulation)L、DD(Definition)L语句。

  * 返回值：DML语句返回影响的行数，DDL执行成功也可能返回0。

  * 对于DML语句，可以通过返回值是否>0判断是否修改成功，返回值为0则代表修改失败。

  * 对于DDL，执行后不报异常即可认为执行成功。

    ```java
     @Test
        public void testDML() throws Exception {
            // 2.建立连接，获取连接对象。
            String url = "jdbc:mysql:///db_demo1?useSSL=false";
            String username = "root";
            String password = "mysql";
    
            Connection conn = DriverManager.getConnection(url, username, password);
            // 3.定义sql
            String sql = "UPDATE account SET money = 3000 WHERE id = 5;";
    
            // 4.获取statement对象
            Statement statement = conn.createStatement();
    
            // 开启事务
    
            try {
                conn.setAutoCommit(false);
    
                // 5.执行sql，返回值为受影响的行数。
                int count = statement.executeUpdate(sql);
                if (count > 0) {
                    System.out.println("修改成功");
                } else {
                    System.out.println("修改失败");
                }
                System.out.println(count);
    
                // 提交事务
                conn.commit();
            } catch (Exception throwables) {
                // 回滚事务
                conn.rollback();
                throwables.printStackTrace();
            }
    
            // 6.释放资源
            statement.close();
            conn.close();
        }
    ```



#### ResultSet

结果集对象，封装了DQL的查询结果。

* 该对象用于获取查询结果：

  该对象类似迭代器，共有2类方法：

  * boolean next()：
    * 让游标从当前位置向前移动，第一次移动之后获取第一条数据。
    * 返回值代表当前行是否为有效行。
  * `getXXX(参数)`方法：
    * 根据获取的数据类型不同，有getInt, getString, getArray等不同方法。
    * 会根据参数类型进行重载overload：
      * int：列的编号，从1开始。
      * String：列的名称。

```java
  @Test
    public void testResultSet() throws Exception {
        String url = "jdbc:mysql:///db_demo1?useSSL=false";
        String username = "root";
        String password = "mysql";

        Connection conn = DriverManager.getConnection(url, username, password);
        Statement statement = conn.createStatement();

        String sql = "select * from account";
        // 执行sql
        ResultSet resultSet = statement.executeQuery(sql);
        // 通过循环获取所有结果。
        // 光标向下移动一次，获取第一个结果。

        List<Account> list = new ArrayList<>();
        while (resultSet.next()) {
            // 通过列的编号获取
            // int id = resultSet.getInt(1);
            // String name = resultSet.getString(2);
            // double money = resultSet.getDouble(3);
            // 通过列名获取
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double money = resultSet.getDouble("money");

            // System.out.println("id:" + id + "\tname:" + name + "\tmoney:" + money);
            Account account = new Account();
            account.setId(id);
            account.setName(name);
            account.setMoney(money);

            list.add(account);
        }

        System.out.println(list);

        // 释放资源
        resultSet.close();
        statement.close();
        conn.close();
    }
```

### PreparedStatement

预编译SQL语句的对象，用于防止SQL注入。

* sql注入演示 略

* PreparedStatement的使用：

  1. 从connect对象获取preparedStatement对象，获取对象时传入sql语句。

  2. 传入的sql语句中用`?`来作为参数的占位符。

  3. 通过preparedStatement对象的setXXX方法来设置参数

     `setXXX(编号,参数值)`：XXX为参数类型，第一个参数为第几个`?`占位置，第二个参数为参数值。

  4. 通过execute方法执行sql语句，此时无需传入sql语句。

```java
 @Test
    public void testPrepared() throws Exception {
        String url = "jdbc:mysql:///db_demo1?useSSL=false";
        String username = "root";
        String password = "mysql";

        Connection conn = DriverManager.getConnection(url, username, password);

        // 用户登录的用户名和密码。
        String name = "zhangsan 123123";
        String pwd = "' or '1' = '1";
        String sql = "select * from tb_user where username=? and  password=?";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        // 编号, 参数值
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, pwd);

        ResultSet resultSet = preparedStatement.executeQuery();
        // 如果有数据，则代表登录成功，没有数据代表登录失败。
        if (resultSet.next()) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }

        // 释放资源
        resultSet.close();
        preparedStatement.close();
        conn.close();
    }
```

* 原理：会对参数中所有特殊字符进行转义。

#### 预编译

* 通过在连接数据库时，url后拼接`useServerPrepStmts=true`键值对开启预编译功能。
* 开启预编译后，创建PreparedStatement对象传入sql语句时会进行预编译，之后通过execute方法传入不同参数执行多次时，可以避免重复执行sql语句的检查和编译阶段。

## IDEA使用

`ctrl+alt+v`：相当于.var的快捷键。

ctrl+shift+上/下：将代码上下移动
