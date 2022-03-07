# VueX

## 组件间共享数据的方式

* 父向子传值：props

* 子向父传值：v-on 事件绑定

* 不相干组件共享数据：EventBus

  这些方案都只适用于小范围的组件间数据共享

  

  VueX是实现组件全局状态（数据）管理的一种机制，可以方便的实现组件间数据共享。

  能够高效的实现组件间的数据共享。

* 安装`npm i  install vuex`

## Vuex的核心概念

* State

  State提供唯一的公共数据源，所有共享的数据都要放到Store的State中存储

  * 组件访问State中的数据：

    * `this.$store.state.全局数据名称`jinxing

    * 通过mapState函数将state映射为计算属性

      mapState返回的是一个对象，可以通过传入字符串来获取对应的state中数据的值

      可以通过展开运算符放到计算属性中

      ```js
      computed: {
          ...mapState(['count'])
      }
      ```

      或直接赋值给计算属性

      ```js
      computed:mapState(['count'])
      ```

      直接赋值给计算属性可以对state中属性进行重命名

      ```js
      computed:
      mapState({
          myCount:state=>state.count
      })
      ```

* Mutation

  > 更改 Vuex 的 store 中的状态的唯一方法是提交 mutation。 

  不应该直接在组件中修改store中的数据。

  应该通过`mutations`中定义的函数，来修改state中的数据

  * 在组件中通过`this.$store.commit('函数名')`调用Mutation中的函数，以字符串的形式提交函数名。
  * Mutation函数的第一个形参应为state，以修改state中的数据
  * Mutation函数的第二个形参为参数对象，以参数对象的属性的方式传入其他形参。

  * 另一种调用Mutation函数的方法：mapMutations函数

    使用展开运算符和mapMutations函数，将Mutation函数映射为methods中的方法。

    对于有载荷的情况也会映射载荷。

    ```js
    methods: {
        ...mapMutations(['函数1','函数2','函数3'……])
    }
    ```

    

  ​	

  ​	

* Action

* Getter