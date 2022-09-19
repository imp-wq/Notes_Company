## generator与Promise

* 通过在generator中生成Promise对象，在then方法中调用iterator的next方法，来进行异步操作。

* 通过递归函数，让Promise generator一直执行，直到所有异步操作执行完毕（不论有多少个promise）。

* 本质上async await是这种写法的语法糖。

  ```js
  function* getData() {
      const res1 = yield requestData('data2')
      console.log(res1)
      const res2 = yield requestData(res1 + 'data3')
      console.log(res2)
      const res3 = yield requestData(res2 + 'data4')
      console.log(res3)
  
  }
  
  function execGeneratorFn(genFn) {
      const generator = genFn()
      exec()
  
      function exec(res) {
          const result = generator.next(res)
          if (result.done) return
          result.value.then(res => {
              exec(res)
          })
      }
  }
  
  execGeneratorFn(getData)
  ```

## await async 事件循环

​	asynchronous，异步。

### async异步函数

* 异步函数的执行过程默认情况下和普通函数一致，默认情况下被同步执行。

* 异步函数会返回一个Promise：

  * 普通值会等价于被Promise.resolve方法包裹（默认返回`Promise.resolve(undefined)`）。
  * 返回一个Promise对象：由该Promise对象决定异步函数的Promise状态。
  * 返回一个thenable的值：等到then方法执行完毕后，异步函数才返回结果。

* 异步函数的异常会作为Promise的reject进行传递，即可以通过返回的Promise的catch方法进行捕获异常，有别于普通函数中的异常。

  ```js
  fn().catch(err => console.log(err))
  ```

### await关键字

​	await关键字只能用在asynchronous函数中。

* await后跟Promise表达式，会等到Promise为fulfiled状态后，才继续执行。



