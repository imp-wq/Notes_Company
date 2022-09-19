## Promise

### then的返回值

* then方法返回一个新的Promise。等价于在Promise的回调中resolve中，传入了then回调函数的返回值。
* catch的返回值也会进行同样的处理，因此catch之后也可以通过then进行链式调用。

### Promise的静态方法

* resolve：

  等价于创建一个Promise并执行resolve操作。

  ```js
  Promise.resolve('hello')
  // 等价于
  new Promise(resolve => resolve('hello'))
  ```

* reject

  ```js
  Promise.reject('hello')
  // 规范：如果需要某个不使用的形参占位，可以使用_。
  new Promise((_, reject) => reject('hello'))
  ```

* all:
  * 参数可以是数组，也可以是其他iterable可迭代的对象。
  * 如果所有的Promise都fulfilled，则将结果组成一个数组传入。
  * 如果有一个Promise reject，会直接reject并将首个reject结果传入。

* allSettled：ES11（ES2020）新增，all只要有一个Promise reject，其他结果都会被丢弃。

  allSettled会等到所有Promise都有结果才决议，且结果一定是fulfiled Promise。

  结果数组中每个元素是一个对象：

  * status：string类型，fulfiled或rejected。
  * value/reason：成功的结果/失败的原因。

* race

  存在问题：无论结果是fulfiled还是rejected，都会导致拿不到其他Promise的结果。

* any：类似race，但要等到第一个状态为fulfiled才会返回其结果。如果所有Promise都reject，则会等到最后一个Promise reject之后才转为rejected（内部创建错误信息：All promises were rejected. ）。



