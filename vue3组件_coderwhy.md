# Vue3组件 coderwhy

## 组件传参

* 在props数组中，如果传入的是对象类型，设置默认值default时应该使用一个函数返回一个对象，而不是一个对象。

  这是因为如果默认值是一个对象的引用，在多次使用同一个组件时，将都初始化为**同一个对象的引用**。

* 在使用props数组中的属性传参时，应该将camelCase命名转化成等价的kebab-case命名，是因为html中的属性（attribute）是大小写不敏感，浏览器在执行时会把所有的大写字符转换成小写字符。

* 非props的attribute

  在向组件传入非props的属性，比如class、style、id等属性时：

  * 当组件有单个根节点时，属性会自动添加给根节点

    可以在组件中设置`inheritAttrs:false`禁用根节点继承属性

  * 可以通过`$attr`来访问所有非props的属性

### 自定义事件

* 大体跟vue2相同，但是需要在emit选项中注册组件发出的自定义事件

* emit选项的作用：

  emit可以为数组形式，也可以为对象形式

  如果为对象形式，可以将每个自定义事件写成函数形式，为传入的参数进行验证。函数返回值为true为验证成功，返回值为false为验证失败。

  也可以传入一个null不进行验证

## 非父子组件通信

### Provide/Inject

可以看成是'long range props'，方便多层的父子关系之间来传递数据（比如爷孙关系）

* 父组件通过provide选项提供数据，不需要知道哪些子组件使用了这些数据。

  * 直接在provide选项中传入对象即可

    ```js
    provide:{
            name:'dog',
            age:22
        },
    ```

  * 如果provide需要用到data中的数据，则需要将其写成函数形式，以便用this访问到组件的实例对象

    因为对象形式不算作用域，而函数可以在执行时用bind绑定this

  * provide默认绑定的数据都是非响应式的，如果需要绑定响应式数据，需要引入vue的computed函数，并在使用时取value属性

    ```js
    provide(){
        return {
            name:this.name,
            age:computed(()=>this.age) // 使用箭头函数以便this指向组件实例
        }
    ```

* 子组件通过inject选项来使用数据，不需要知道这些数据来自哪里。

### Mitt全局事件总线

官方推荐的库：mitt和tiny-emitter

类似vue2的eventbus

* eventbus.js的写法

  ```js
  import mitt from 'mitt'
  const emitter = mitt()
  export default emitter
  ```

* 触发自定义事件

  引入eventbus后，利用emmiter.emit方法触发自定义事件。

  实际开发中，可以将自定义事件名以常量形式单独保存在一个文件中。

  ```js
  import emmiter from '../utils/eventbus'
  export default {
      method
          btnClick() {
              emmiter.emit('自定义事件名',参数)
          }
      }
  }s
  ```

* 监听自定义事件

  利用emmiter.on方法监听自定义事件，在组件的created生命周期监听事件

  事件名可以传入`*`来监听所有自定义事件

  ```js
  import emitter from '../utils/eventbus'
  export default {
      created() {
          emitter.on('abc',(info)=>{
              console.log(info)
          })
      }
  }
  ```

* 取消监听自定义事件

  * 取消所有 `emitter.all.clear()`

  * 取消某一个事件，要求在监听时传入了事件处理函数

    利用emitter.off方法取消监听

    ```
    funciton fn
    emitter.on('foo',fn)
    emitter.off('foo',fn)
    ```

## 插槽

用处：让使用者决定某一区域到底存放什么内容和元素

* 插槽的标签是`<slot>`，利用name属性为插槽指定名称

  没有指定name的插槽，默认名为default

* 使用具名插槽：使用`v-slot：插槽名称`指令指定插槽

  * v-slot只能用在template标签上。

    只有独占默认插槽例外：

    当子组件只有默认插槽、父组件传入一个组件时，可以将v-slot写在组件标签上。

  * `v-slot:`可以简写为`#`

* 动态插槽名：

  子组件：`<slot :name="name">` 使用v-bind动态绑定name属性

  父组件：`<template v-slot:[name]>` 来为v-slot指定绑定变量

## 作用域插槽

* 渲染作用域：

  父级模板的所有内容都是在父级作用域中编译的，子级模板的所有内容都是在子级作用域中编译的

  即，虽然插槽显示在子组件中，但插槽只能访问父组件的property，不能访问子组件的property

* 作用域插槽：

  允许插槽访问子组件中的property

  * 子组件：将子组件的property绑定为`<slot>`标签的attribute

    ```html
    <slot :item="item" :index="index"></slot>
    ```

  * 父组件：v-slot的值是一个对象，通过该对象拿到子组件的属性

    ```html
    <template v-slot:default="slotProps">
        {{slotProps.item}}
        {{slotProps.index}}
    </template>
    ```

