### 组合函数

先跳过自动组合函数

### with和eval

不建议使用。

* with语句：用于扩展作用域，严格模式中不允许使用。

  通过with可以直接访问obj中的message字段。

  ```js
   const obj = { message: 'hello' }
   with (obj) { console.log(message) }
  ```

* eval函数：内建函数eval可以将传入的字符串当作js代码执行，将最后一句代码的值作为返回值。可以访问外部变量。

### 严格模式

​	长期以来，js都是不断发展且向前兼容的，这造成以前一些不完善的语法也永远保留在js中。

​	Strict Mode，使得代码脱离松散（sloppy）模式。

* 粒度范围有全局和函数。

* 一般情况下打包后的代码都是默认开启严格模式的。
* 在class和module中都会开启严格模式。
* 语法限制：
  * 变量需先声明再使用。
  * 静默失败：
    * 对于使用Object.defineProperty定义writable为false的变量，写入时会直接报错。
    * 删除configurable为false的属性，会报错。
  * 函数参数不能有相同名称。
  * 不允许0开头表示八进制（应用0oxxx表示八进制）。
  * 不允许使用with，eval不会为上层引入变量。
  * this不会被包装成对象，函数独立执行不会指向window对象，而是undefined。

## 对象增强

### 对象属性描述符

​	如果需要对对象的属性进行控制，需要用到属性描述符。

* 属性描述符通过Object.defineProperty来添加和修改。

  `Object.defineProperty(obj, prop, descriptor)`

* 也可以通过defineProperties批量修改。

  ```js
  Object.defineProperties(obj, {
      name: {
          writable: true,
      },
      age: {
          enumerable: false
      }
  })
  ```

  

* 分类：

  * data properties descriptor：数据属性描述符
  * accessor properties descriptor：存取属性描述符

* 属性描述符：

  data properties descriptor：

  * configurable：该属性是否能被delete删除、能否再次被defineProperty进行配置。

    >when this is set to `false`, other attributes of its descriptor cannot be changed.

    defaults to false. 

    ​	这里默认值指的是使用defineProperty定义属性时的默认值，而非未使用defineProperty的普通对象中的默认值。

  * enumerable：可枚举，该属性能否被for-in循环或Object.keys获取到。

    defaults to false.

  * writable：可写。defaults to false.

  * value: 值。defaults to undefined.

  access properties descripter:

  * set: 
  * get: 访问属性时调用。
    * 不能直接对原对象的字段进行设置，会造成无限递归。
    * 可以设置另一个私有变量_obj，返回其字段。

* [value, writable]和[get, set]这两组keys不能存在于同一个属性的descriptor上，否则会抛出异常。

  即一个属性的descriptor要么属于数据修饰符，要么属于访问修饰符。

* 其他方法：

  * `Object.getOwnPropertyDescriptor(obj, 'name')`：获取该属性的descriptor。

  * `Object.getOwnPropertyDescriptors(obj)`：获取对象所有的descriptor。

  * ` Object.preventExtensions(obj)`：不允许为对象新增字段。

  * `Object.seal(obj)`：密封对象，等于preventExtensions，再将所有字段的configurable置为false。

    只能修改字段的value（writable），不能增添、删除、配置字段。

  * `Object.freeze(obj)`：冻结对象，在seal的基础上将所有字段writable置为false。

  