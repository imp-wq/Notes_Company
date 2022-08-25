## for of和for in

[ES6语法六之for..of循环 - 掘金 (juejin.cn)](https://juejin.cn/post/7135249879416668191)

* for...of为es6新增语法，用于遍历iterable的值
* for...in用于在数组的index上循环。

### The iterator protocol

​	An object is an iterator when it implements a **`next()`** method with the following semantics:

* All iterator protocol methods (`next()`, `return()`, and `throw()`) are expected to return an object implementing the `IteratorResult` interface. It must have the following properties:
  * done:  `True` indicates the iterator has yielded its last value.
  * value: when `done` is true, can be omitted.  

* iterator & iterable

### function *

​	`function` keywords followed by a asterisk, defines a generator function, which returns a `Generator` object.

* do not have arrow function counterparts. 
* 

### Generator

* The Generator object conforms both the iterable protocol and the iterator protocol.
* The Generator constructor is not available globally. Instance of Generator must be returned from `generator functions`. 