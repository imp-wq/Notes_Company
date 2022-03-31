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

  