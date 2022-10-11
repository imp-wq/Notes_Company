## 数据库连接池

数据库连接池是一个容器，负责分配、管理数据库的连接。

* 好处：

  * 资源重用
  * 提升系统响应速度
  * 避免数据库连接遗漏

* 标准接口：DataSource

* 常见数据库连接池：

  * DBCP
  * C3P0
  * Druid：是阿里巴巴开源数据库连接池项目。

* 基本使用：

  1. 导入jar包，add as library。

  2. 配置文件

     ```properties
     driverClassName=com.mysql.jdbc.Driver
     url=jdbc:mysql:///db1?useSSL=false&useServerPrepStmts=true
     username=root
     password=1234
     # 初始化连接数
     initialSize=5
     # 最大连接数
     maxActive=10
     # 最大等待时间
     maxWait=3000
     ```

* 查看当前项目的目录，以便于获取配置文件的路径

  ```java
  System.out.println(System.getProperty("user.dir"));
  ```

## CRUD练习

* 推荐在java数据库对应的模型类中，基本数据类型全部使用包装类。

  这可以让默认值均为null，而不会是各个基本类型的默认值（比如int默认值为0）。

* 如果sql有字符串拼接， 一定要注意字符串拼接处的空格位置，保证关键字前后至少留有一个空格。

* 思路：

  1. 获取connection，druid连接池
  2. 定义sql语句
  3. 获取PreparedStatement对象
  4. 设置参数
  5. 执行sql语句
  6. 处理结果
  7. 释放资源，[Resultset], statement, connection 

```java
   /**
     * 查询所有
     */
    @Test
    public void testSelectAll() throws Exception {

        // System.out.println(System.getProperty("user.dir"));
        // 获取数据库连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/com/itheima/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();

        String sql = "select * from tb_brand";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        List<Brand> brands = new ArrayList<>();

        ResultSet resultSet = preparedStatement.executeQuery();
        Brand brand = null;
        while (resultSet.next()) {

            int id = resultSet.getInt("id");
            String brandName = resultSet.getString("brand_name");
            String companyName = resultSet.getString("company_name");
            int ordered = resultSet.getInt("ordered");
            String description = resultSet.getString("description");
            int status = resultSet.getInt("status");

            brand = new Brand();
            brand.setId(id);
            brand.setBrandName(brandName);
            brand.setCompanyName(companyName);
            brand.setOrdered(ordered);
            brand.setDescription(description);
            brand.setStatus(status);

            brands.add(brand);
        }

        System.out.println(brands);

        //    释放资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    /**
     * 添加
     * 参数：除了id之外所有参数
     * 结果：boolean是否添加成功
     */
    @Test
    public void testAdd() throws Exception {
        // 页面接收的参数
        String brandName = "香飘飘";
        String companyName = "香飘飘";
        int ordered = 1;
        String description = "绕地球一圈";
        int status = 1;


        // 获取数据库连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/com/itheima/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();

        String sql = "insert into tb_brand(brand_name, company_name,ordered,description,status) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 设置参数
        preparedStatement.setString(1, brandName);
        preparedStatement.setString(2, companyName);
        preparedStatement.setInt(3, ordered);
        preparedStatement.setString(4, description);
        preparedStatement.setInt(5, status);

        int count = preparedStatement.executeUpdate();

        // 处理结果
        if (count > 0) {
            System.out.println("执行成功");
        } else {
            System.out.println("执行失败");
        }

        //    释放资源
        preparedStatement.close();
        connection.close();
    }

    /**
     * 修改
     * 参数：所有数据，根据id查找。
     * 结果：boolean，是否修改成功
     */
    @Test
    public void testUpdate() throws Exception {
        // 页面接收的参数
        String brandName = "香飘飘";
        String companyName = "香飘飘";
        int ordered = 1000;
        String description = "绕地球三圈";
        int status = 1;
        int id = 4;

        // 获取数据库连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/com/itheima/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();

        // 一定要注意字符串拼接处的空格位置，保证关键字前后至少留有一个空格。
        String sql = "update tb_brand set " +
                "brand_name = ?," +
                "company_name = ?," +
                "ordered = ?," +
                "description = ?," +
                "status = ? " +
                " where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 设置参数
        preparedStatement.setString(1, brandName);
        preparedStatement.setString(2, companyName);
        preparedStatement.setInt(3, ordered);
        preparedStatement.setString(4, description);
        preparedStatement.setInt(5, status);
        preparedStatement.setInt(6, id);

        int count = preparedStatement.executeUpdate();
        // 处理结果
        if (count > 0) {
            System.out.println("执行成功");
        } else {
            System.out.println("执行失败");
        }

        //    释放资源
        preparedStatement.close();
        connection.close();
    }

    /**
     * 删除
     * 参数：id
     * 结果：boolean，是否删除成功
     */
    @Test
    public void testDeleteById() throws Exception {
        // 页面接收的参数
        int id = 4;

        // 获取数据库连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/com/itheima/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();

        // 一定要注意字符串拼接处的空格位置，保证关键字前后至少留有一个空格。
        String sql = "delete from tb_brand where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 设置参数
        preparedStatement.setInt(1, id);

        int count = preparedStatement.executeUpdate();
        // 处理结果
        if (count > 0) {
            System.out.println("执行成功");
        } else {
            System.out.println("执行失败");
        }

        //    释放资源
        preparedStatement.close();
        connection.close();
    }
```

