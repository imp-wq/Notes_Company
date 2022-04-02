## Mixin

* 用于在组件和组件之间对相同的逻辑进行抽取

* 一个mixin对象可以包含组件的所有选项，当组件使用mixin对象时，所有mixin对象的选项将被混合进入组件本身的选项中

* 在mixins选项进行使用，数组类型

  ```js
  mixins: [mixin对象名]
  ```

* 全局混入：为全局根组件app设置mixin选项

* extends继承的功能类似mixin，可以继承一个组件的选项

## Composition API

* 可以用setup选项代替大多数选项是API

* 在setup中不能使用this

* setup的参数：

  * props：父组件传递的属性

  * context：包含三个属性

    * attrs：非props的attribute
    * slots：插槽
    * emit：用来触发事件（因为不能用this.$emit触发事件了）

    可以直接以解构的形式传入

    ```js
    setup(props,{ attrs,slots,emit }) {}
    ```

* setup的返回值：

  可以在template使用，可以用来代替data选项。

* setup中不能使用this

  因为在setup中没有绑定this

### setup中的响应式API

* reactive

  对传入的类型有限制，必须是对象或数组。

  将变量用reactive包裹就会变成响应式。即将变量作为一个对象的属性，将这个对象传给reactive。

  ```js
  const obj = reactive({
      count: 0 // count就是响应式了
  })
  ```

  会对响应式的变量进行劫持，收集依赖他的变量。

  data选项中的数据会用reactive进行处理，以获得响应式。

* ref API

  reference，ref会返回一个可变的响应式对象来维护内部的值。内部的值保存在ref的value属性中。

  ```js
  const num = ref(0) // 返回的是一个响应式对象的引用
  ```
  
  * 在模板中使用ref对象时，会进行自动浅解包。
  * 将ref作为property赋给一个reactive响应式对象时，也会自动进行浅解包，使其用起来像一个普通的property
  
* readonly API

  用于将一个响应式对象设置为只读。会返回对象的只读Proxy（只劫持set方法，使其不允许被修改）

### toRef和toRefs

直接对响应式对象进行解构赋值会使其失去响应性。

```js
const info = reactive({name:'aaa',age:18})
let {name,age} = info // name和age会失去响应性
```

* toRefs

  用于响应式对象的解构赋值，传入一个响应式对象，将对象本身变成普通对象（用于解构），将其property都转换成ref

  ```js
  let {name,age} = toRefs(info) // 得到的name和age都是ref对象
  ```

  响应式会保证解构的ref对象和原响应式对象的属性“链接”起来，其中一个的改变会影响另一个。

* toRef

  用于将响应式对象中的某个属性转换成ref对象

### computed和watch

* 

### 其他响应式API

* unRef：val = isRef(val) ? val.value : val 的语法糖

  isRef：判断一个对象是否是ref对象

* shallowRef 创建一个浅层Ref

  triggerRef 手动执行shallowRef的副作用

* customRef 自定义Ref，自定义什么时候触发



## 开发中遇到的一些问题

* 作用域问题，何时使用块级作用域
* 对于想要全局挂载的属性，global property如何处理
* 通过ref获取DOM

