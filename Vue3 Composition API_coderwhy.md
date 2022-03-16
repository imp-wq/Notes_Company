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

* setup中的响应式API

  