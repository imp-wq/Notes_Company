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

  Mutation中应该是同步函数，不要在Mutation中执行异步操作。

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

* Action

  Action用于进行异步任务。

  Action中也应该通过触发Mutation来变更数据。

  * Action函数中通过形参context传入store对象，通过context.commit访问Mutation函数

  * 传参类似于Mutation函数，但要先通过dispatch函数将参数传递给Action函数，再通过Action将参数传递给Mutation函数

    ```js
    addAsync(context,N) {
                setTimeout(() => {
                    context.commit('addN',N)
                }, 1000);
            }
        }
    ```

  * 在组件中触发Action函数：

    * 通过dispatch函数触发Action函数

      `this.$store.dispatch('Action函数名')`

    * 通过mapActions函数，将指定的actions函数，映射为当前组件的methods函数

      ```
      methods:{
      	...mapActions(['addAsync','addNAsync'])
      }
      ```

* Getter

  Getter用于对store中的数据进行加工处理，形成新的数据，类似Vue的计算属性。

  * 在组件中访问Getter

    * `this.$store.getters.名称`

    * 使用matGetters映射为组件的计算属性

      `...mapGetters(['showNum'])`



