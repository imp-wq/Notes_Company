## koa2和express

* 支持async await处理异步任务
* 支持洋葱模型的中间件

## 请求和响应

* 通过app.use()方法进行响应

  ```js
  import koa from 'koa'
  
  const app = new koa()
  app.use((ctx,next)=>{
    console.log(ctx.request.url)
    ctx.response.body='hello world'
  })
  
  app.listen(3000)
  ```

  * app.use中的函数,通过ctx.request拿到请求,通过ctx.response对响应进行设置

* 洋葱模型的中间件：
  * 上一层中间件使用`next()`对下一层中间件进行调用
  * 下一层中间件调用完毕后，会返回到上一层中间件`next()`的位置
  * `next()`是上一层中间件使用`next()`对下一层中间件进行调用，因此也可以用于接收下一层中间件的返回值。但返回的数据会被包装成promise对象，因此可以用async-await进行接收。

## echart项目后台

* 思路

  1. 项目准备
  2. 总耗时中间件
  3. 响应头中间件
  4. 业务逻辑中间件
  5. 允许跨域

* 响应头中间件

  * 需要放在第一层，以便记录响应时间 

  * 通过`ctx.set('字段','值')`方法设置响应头的X-Response-Time字段

    ```js
    ctx.set('X-Response-Time', `${duration}ms`)
    ```

* 跨域

  * 指的是当前页面地址和ajax获取数据地址，同协议同域名同端口

  * 设置响应头：

    ```json
    {
        "Access-Control-Allow-Origin":"*",
         "Access-Control-Allow-Methods":"OPTION,GET,PUT,POST,DELETE",
    }
    ```











