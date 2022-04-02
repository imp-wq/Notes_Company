# Promise

* promise对象在新建后会立即执行
* promise函数中的resolve和reject，用于改变promise的状态为fulfiled或rejected，同时进行传参（只能传一个参数）。resolve和reject调用过一个使promise的状态改变后，promise的状态将凝固，即再调用是无效的。
* promise对象中的函数，返回值应该是无效的，不会被任何地方接收。
* promise可以通过then方法指定resolve和reject回调函数，然后在**Promise中的逻辑执行结束**后，根据其状态（fulfiled或rejected）来调用。
  * then可以接收两个函数，resolve和reject函数，这两个函数都只能接收**一个参数**，该参数可以看成是上一次异步操作的结果。通过在promise对象的函数中调用resolve和reject时传入。
  * then方法的两个函数，都一定会返回一个promise对象。如果直接返回一个值等，会被包装成一个fulfiled的promise对象。可以手动return new Promise以设置其状态。

## async\await语法糖

* async函数返回一个Promise对象

* async内部return语句的返回值，会成为then方法回调函数的参数

* async返回的Promise对象的then方法，必须要等async函数内部的所有异步操作都执行完，才会执行

* async函数中任何一个await语句后的Promise对象变为reject状态，整个await函数都会停止执行

* 通过函数返回一个promise对象时，加上await拿到的值是其在调用resolve()时传入的值

  ```js
  const fn = () => {
      return new Promise((resolve, reject) => {
          resolve(432) // 这里是只可以传入一个参数的
      })
  
  const test = async () => {
      const p = await fn() // 这里拿到的p是432
      console.log('p:',p)
  }
  test()
  ```

  