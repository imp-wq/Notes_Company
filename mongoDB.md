# MongoDB

查询的时候要注意数据类型是number还是字符串。

### 一些查询运算符

* $gt:大于

* $lt:小于

* $gte:大于等于

* $lte:小于等于

* $eq:等于

* $or：后头放一个数组，数组里头的条件满足一个就行

  ```js
  // 查找工资小于1000或大于2500的
  db.collection.find({$or:[{sal:{$lt:1000}},{sal:{$gt:2500}}]})
  ```

* $inc:增加

## 一些常用函数

* limit(数量)：设置显示数据的上限	

  `db.集合名.find(查询条件).limit(10)`查询数据的最多显示10条，一般用于用于翻页显示等等

  * 开发中应避免直接进行不带条件的查询，比如`db.集合名.find()`
    * 查询慢
    * 查询结果太多，占用内存太高
    * 发送的数据太多，网络资源占用太多
    * 用户数据接收的数据太多，用户电脑内存占用太多

* skip(数量)：用于跳过指定数量的数据

  `db.集合名.find(查询条件).skip(10).limit(10)`可以用于显示第二页的数据

MongoDB会自动调整skip和limit的位置，即`db.collection.find(查询条件).skip(10).limit(10)`和`db.collection.find(查询条件).limit(10).skip(10)`效果一样

## 文档之间的关系

* 一对一one to one

  可以通过内嵌文档的形式体现

* 一对多one to many

  可以通过内嵌文档数组的形式体现

  也可以使用外键的形式。

  比如客户-订单的一对多关系，可以在订单的表中，加上客户的id字段，通过客户的id查找每个客户的订单。

  ```js
  // 根据用户名查找该用户对应的订单
  var userID=db.users.findOne({username:'swk'})._id
  db.order.find({user_id:user_ID});
  ```

  

* 多对多many to many

  使用外键的形式，加入多个外键字段，即形成多对多关系

  ```js
  // 以数组形式插入多个老师的id，形成多对多的关系
  db.stus.insert({
      name:"studentName",
      teacher_ids:[
          ObjectId("teacher1"),
          ObjectId("teacher2"),
          ObjectId("teacher3"),
      ]
  })
  ```

## 排序

* 默认排序是根据id排序，即按创建时间排序

* sort进行排序：

  `db.集合名.find({查询规则}).sort(排序规则)`

  传入排序规则，1代表升序，-1代表降序

  ```js
  // 按工资升序排列
  db.collection.find({}).sort({sal:1})
  // 先按照工资进行升序排列，工资一样的按照部门编号降序排列
  db.collection.find({}).sort({sal:1,empno:-1})
  ```

limit、skip、sort可以以任意顺序进行调用，实际执行时sort会被最先调用

## 投影

find函数可以传入第二个参数作为投影，即想显示哪些字段

```js
// 只显示员工的姓名ename和薪水sal，id默认情况下都显示，除非手动设置_id:0
db.emp.find({},{ename:1,sal:1})
```

## Mongoose

​	node可以用原生MongoDB模块操作数据库，Mongoose是对MongoDB模块的封装。

* Mongoose是一个对象文档模型（ODM）库

* Mongoose的好处：

  * 可以为文档创建一个模式结构Schema，进行数据约束
  * 对模型中的对象/文档进行验证，对数据类型进行转换
  * 使用中间件来与业务逻辑挂钩

* mongoose中的几个对象：

  * Schema模式对象

    约束数据库中的文档结构

  * Model

    对应MongoDB数据库中的collection

  * Document

    对应数据库中具体的数据

### 使用mongoose

* 安装 `npm i mongoose`

* 引入

  ```js
  const mongoose=require('mongoose')
  ```

* 连接数据库 connect方法

  ```js
  // 协议 ip:端口号（默认27017） 数据库名
  mongoose.connect('mongoose://localhost/test',{useMongoClient:true})
  ```

  可以通过mongoose对象的connection属性来监听数据库的连接

  ```js
  // 监听数据库连接事件
  mongoose.connection.once('open',()=>{})
  // 监听数据库断开事件
  mongoose.connection.once('close',()=>{})
  ```

  ​	可以使用dicconnect方法断开连接。但MongoDB数据库一般情况下只需要连接一次，项目停止或服务器关闭时才断开。

###  创建Schema、Model和collection

​	创建顺序：Schema->Model->collection

* 创建Schema

  使用mongoose.Schema类

  ```js
  const Schema=mongoose.Schema
  const stuSchema=new Schema({
      name:String,
      age:Number,
      gender:{
          type:String,
          default:'female' // 设置默认值
      },
      address:String
  })
  ```

* 创建Model

  使用mongoose.model(数据库集合名，Schema)方法

  ```js
  // 映射到数据库中student集合，使用stuSchema这个Schema
  const StuModel=mongoose.model('student',stuSchema)
  ```

* 向数据库中插入文档

  Model的create(doc,callback)方法

  * 回调函数会接收两个参数

    第一个参数err是错误，没有错返回null

    第二个参数为一个数组，就是插入的文档

  * 插入到数据库中时，mongoose会自动将集合名变为复数

  ```js
  StuModel.create({
      name:'syk',
      age: 18,
      gender: 'male'
  },(err)=>{
      if(!err) {
          console.log('插入成功')
      }
  })
  ```

### 在mongoose中进行增删改查

#### 查

* Model.find(condition,[projection],[option],[callback])

  * projection：投影，设置显示哪些字段

    ```js
    // 可以样以对象形式设置
    {name:1,_id:0}
    //  也可以以字符串的形式设置
    'name age -_id' // 用-设置不显示的
    ```

  * options：查询选项，可以设置skip和limit

  * callback：通过回调函数返回结果，因此其实是必传的参数。

    接收两个参数：错误err，查询结果docs

    ```js
    StuModel.find({name:'ts'},(err,docs)=>{
       	if(!err) {
            // 对于find，返回的查询结果是一个数组，查不到返回空数组
            console.log(docs[0])
        }
    })
    ```

* findOne：参数同上，只返回一个对象，而不是数组

* findById：查询参数只需传入id（一个字符串，而不是一个对象），只查询一个文档，因此也只返回一个对象

通过查询返回的对象，就是Document文档对象，是Model的实例，也可以调用Model的方法。

#### 改

* Model.update(condition,doc,[option],[callback])
  * doc：修改后的对象，可以使用运算符
* updateOne，updateMany，replaceOne

#### 删

* remove，deleteOne，deleteMany

#### 其他

* Model.count(conditions,callback)：统计文档数量
* 