# css高级

## 过渡

​	从一个状态渐渐过渡到另一个状态

* transition：属性 过渡时间 运动曲线 delay

  * 属性：变化的属性，all代表所有。只能写一个值，要么就写all，要么就逗号分隔

    ```css
    transition:width .5s,height .5s;
    ```

  * 过渡时间：要写单位，s

  * 运动曲线：默认ease，linear匀速，ease-in加速，ease-out减速，ease-in-out先加速再减速

  * delay：延迟几秒开始，默认0s

* 在vue中使用过渡

  * 要使用这种写法，不要用transition把router-view包裹起来。

    将router-view的组件作为参数，传递到作用域插槽中，再在transition组件中接收这个参数，赋值给动态组件的is属性，用动态组件进行组件的切换。

    ```vue
    <router-view v-slot="{ Component }">
      <transition name="fade">
        <component :is="Component" />
      </transition>
    </router-view>
    ```

  * 

  * transition组件要添加name属性，组件在切入\切出的不同阶段，会根据transition的name属性为其添加不同的类名。

  * transition组件还要添加`mode="out-in"`，即当前元素离开后，新元素才开始过渡进入，以防止抖动问题。

  * 切换的路由组件必须只有一个根元素，否则会出现过渡无效的情况。

    

## 转换

​	转换transform的操作包括：

* translate：移动
  * translate(x,y)：x和y为向右和向下移动的距离
  * 也可以用translateX和tranlateY分开来用
  * 不会影响其他元素的位置
* rotate：旋转
* scale：缩放

