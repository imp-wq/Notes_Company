# Iterator-Generator

## 迭代器

### 迭代器iterator

* iterator迭代器：允许用户在容器container中遍历对象的接口，使用时无需关心内部的实现细节。

* JS中的iterator：一个需要符合iterator protocol迭代器协议的对象。

  * iterator对象中的的方法均需要返回一个符合IteratorResult接口的对象。
    * done：是否完成遍历。MDN规定一旦done为true，之后调用next返回对象的done都应为true。
    * value：done为false的时候，返回遍历的值。

  * next方法：调用next方法对容器进行遍历，for...of等方法会调用。
  * return方法：监听迭代器在未完全遍历的情况下的中断。
    * 比如在for..of循环中，如果在遍历完成之前调用了break、return、throw，会触发return方法。
    * 解构时没有解构所有的值。

### 可迭代对象iterable

需实现iterable protocol，即实现了迭代器的对象。

* 需在`[Symbol.iterator]`属性上实现一个函数，返回一个迭代器对象。
* 可迭代对象可以通过`for ... of`循环进行遍历。
* 可用于展开语法（spread syntax）和解构赋值（destruction assignment）。
* 注意：正常情况加对象并非可迭代对象，能使用展开运算符和解构赋值是进行了特殊处理。因此对象无法像数组一样，在作为函数参数时被展开。
* 一些构造函数（Set,Map,WeakSet,WeakMap）/方法（Promise.all, Array.from等）要求传入可迭代对象。
* class中也可以实现[Symbol.iterator]方法，从而使实例对象可迭代。

## 生成器Generator

​	生成器是ES6中新增，对于函数**控制、使用**的方案，可以更加灵活的控制函数什么时候暂停执行、继续执行。

* 生成器实际上是一种特殊的迭代器。

* 基本使用：

  * `function *`

  * 通过yield关键字控制函数执行流程。

  * 调用时会直接返回一个`Generator对象` generator，不执行函数。

  * 生成器对象generator的方法：

    * next：通过generator.next(参数)控制函数执行，当执行到yield语句时，执行完毕yield语句之后，暂停。

      * 调用next时，在所有yield都执行完之后，或遇上return，会将done置为true。

      * 调用时会得到通过next函数传入的参数（就1个参数），会通过上一次暂停的yield语句返回值拿到。

    * return：可以通过generator.return()提前中断生成器的执行。
    * throw：通过`generator.throw()`抛出异常。

    