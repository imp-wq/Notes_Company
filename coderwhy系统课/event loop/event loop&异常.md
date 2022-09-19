## 事件循环

* 进程process：已运行的程序，资源调度的最小单位。

  线程thread：运算调度的最小单位。

* 浏览器一般是多进程的，每个进程又是多线程的，其中包括执行JS的线程。

  JS执行是单线程的。

* setTimeout函数，浏览器会将回调函数push到事件队列，计时任务交给单独的计时线程。

* new Promise传入函数创建Promise对象时，传入的函数会被同步执行。但通过resolve方法调用的then中的方法会被push进事件队列。即，调用resolve会将then中的函数push进事件队列。

  ```js
   new Promise((resolve, reject) => {
       console.log('1111111')
       resolve() // 将then中的函数添加进事件队列。
       console.log('3333333')
   }).then(() => {
       console.log('22222222')
   })
  
  // 打印顺序 111 333 222
  ```

### 微任务 宏任务

* 宏任务macrotask：比如setTimeout的回调，就属于macrotask。

  微任务microtask：比如Promise对象中通过resolve调用then中的方法，就属于microtask。

* 事件队列有2种：

  * 宏任务队列marcotask queue：ajax，setTimeout，DOM addeventListener，UI render

  * 微任务队列microtask queue：Promise的then回调，

    分别用于存放宏任务和微任务。

* 事件循环执行机制：

  * main script中的代码优先执行。
  * 在执行每一个宏任务之前，都需先**清空微任务队列**。

## throw

* throw用于抛出一个异常，抛出异常后js不会继续执行。

* 可以抛出一个字符串提示信息，或一个包含提示信息、错误码的对象。

* Error类是系统通过的错误类，直接throw只会显示错误信息字符串，而throw new Error(msg)还会提示具体的函数调用栈、错误位置等。

  Error类包含3个属性：

  * message：创建对象时传入的message。
  * name：Error的名称。
  * stack：函数调用栈。

* Error有一些默认的子类：

  * RangeError：下标越界异常。
  * SyntaxError：解析语法错误时的异常。
  * TypeError：出现类型错误时的异常。

* try-catch捕获异常：

  * ES10以后catch后的error参数可以省略，即不需要错误对象时，可以直接`catch{}`。
