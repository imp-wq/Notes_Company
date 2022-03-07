# Promise

* promise对象在新建后会立即执行
* promise可以通过then方法指定resolve和reject回调函数，然后在Promise中调用。

## async\await语法糖

* async函数返回一个Promise对象
* async内部return语句的返回值，会成为then方法回调函数的参数
* async返回的Promise对象的then方法，必须要等async函数内部的所有异步操作都执行完，才会执行
* async函数中任何一个await语句后的Promise对象变为reject状态，整个await函数都会停止执行