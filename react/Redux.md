# Redux

 [Redux - A predictable state container for JavaScript apps. | Redux](https://redux.js.org/) 

* Flux和Redux

  Flux架构模式

  * Flux是一种架构思想，利用**单项数据流**的方式更新组件中的数据。
    * 用户访问View
    * View发出Action
    * Dispacher收到Action，要求Store更新
    * Store更新后，发出change事件
    * view收到change事件后，更新页面

  * Flux目前存在至少15种的实现，Redux是其中一种

   Redux

  * Flux的一种实现
  * 使用js设计的，不一定只能跟React一块用

##  Redux的使用

* 安装

  `npm i redux`

* 基本使用

  store模块：

  * 通过createStore(reducer)传入配置好的reducer，创建store对象并导出
  
    初始状态可以通过reducer的默认参数设置，也可以作为createStore的第二个参数传入。
  
  * 其他组件中通过引入store后，使用store.dispatch触发action，在组件创建阶段使用store.subscribe订阅，监听store中状态的变化。
  
    一般情况下，为了保证每次store中的状态更新页面都重新渲染，会将store中的状态保存为组件自己的状态。
  
    在组件销毁阶段，应该对subscribe进行注销，以避免重复订阅。
  
    ```jsx
    const [state, setState] = useState('state')
    useEffect(()=>{
        // 通过subscribe返回值拿到注销订阅的方法
        const unsubscribe = store.subscribe(()=>{
            setState(store.getState().message)
        })
        return ()=>{
            // 在组件销毁阶段注销订阅
            unsubscribe()
        }
    },[])
    ```
  
  * 可以将action对象单独抽离成Action Creator
  
    ```jsx
    const addTodo = text => {
      return {
        type: 'todos/todoAdded', // `domain/eventName`
        payload: text
      }
    }
    ```
  
  reducer的要求：纯函数
  
  * immutable updates(Do not modify existing `state`)
  * No side effects(asynchronous logic……)
  
* reducer可以进行拆分，拆分后每个reducer单独处理不同的属性，再使用combineReducer合并

  ```jsx
  import oneReducer from './xxx'
  import anotherReducer from './xxx'
  import {combineReducers} from 'redux'
  
  const reducer = combineReducers({
      oneReducer,anotherReducer
  })
  
  // 访问时需要加上每个reducer的名称
  console.log(store.getState().oneReducer.property)
  console.log(store.getState().anotherReducer.anotherProperty)
  ```

### 基本原理

* subscribe方法传入的回调函数会被作为依赖记录在列表中
* 执行dispatch时，会先执行reducer（同时传入action）,再执行依赖列表中的所有函数

## Redux中间件

​	redux的actionCreator只能返回一个对象，即无法直接通过dispatch进行异步操作。redux中间件用于在dispatch时进行异步操作。

### redux-thunk

* 使用：在创建store时作为中间件传入

  ```jsx
  const store = createStore(reducer, applyMiddleware(reduxThunk))
  ```

  

* 作用：dispatch的action可以接收一个函数，该函数接收dispatch作为参数，可以使用dispatch在异步操作进行后触发action

  ```jsx
  const actionCreator = (input) => {
    return (dispatch) => {
      setTimeout(() => {
        dispatch({
          type: 'change',
          payload: input
        })
      }, 2000)
    }
  }
  ```

* 原理：将store.dispatch传入action函数

### redux-promise

