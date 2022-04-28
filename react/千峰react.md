## react和react-dom

* react包是react核心包，进行jsx语法的解析等
* react-dom用于将react组件渲染到页面上

## 基础

* react-dom.render(element,dom)函数可以将element渲染到dom节点上，可以直接写html语法
* 组件类：
  * 可以以es6中的类的形式创建react组件，但必须继承react.Component类
  * 通过render方法进行渲染，会在组件实例化之后自动执行
  * 使用时，可以在ReactDom.render方法中，以标签的形式使用组件
  * import引入类组件时，首字母必须大写，以区分react组件和原生DOM元素

* 函数式组件
  * 直接在一个function的return中写jsx语法

## 模板语法

* 在jsx中的{}中写js表达式，类似vue的mustache模板语法
* 在模板语法中使用class为DOM元素绑定类时，应使用className，以避免与class关键字重复
* 标签的for属性应写成htmlFor

## 样式

* css样式可以在js中使用import导入
* react更推荐使用行内样式，因为这样让组件更像是一个整体

## 事件

* 类似传统DOM，在元素上使用on+事件名进行事件绑定，回调函数放在{}中使用js语法，但不要加()

* 通过this在{}中获得class中的方法

* 如果在btn中使用

  ```jsx
  export default class App extends Component {
    render() {
      return (
           {/* 直接在jsx事件中写函数逻辑的写法 */}
          <button onClick={this.btnClick}>事件</button>
           {/* 绑定this <button onClick={this.btnClick.bind(this)}>事件</button> */}
      )
    }
    btnClick() {
        console.log(this)
    }
  }
  ```

  这样的方式绑定事件，需要注意此时的this指向undefined。需要通过bind绑定this。

  另一种使用箭头函数以保证this指向类的写法：

  ```react
  export default class App extends Component {
    render() {
      return (
           {/* 在jsx事件中调用外部函数的写法 */}
         <button onClick={()=>{this.btnClick()}}>箭头函数</button>
      )
    }
    btnClick() {
        console.log(this)
    }
  }
  ```

* react中的事件，本质上都是使用了事件代理，而不是直接绑定到元素本身身上，以节约内存。

* react会构造事件对象

## ref

* 类似vue，用于获取DOM对象

* 应当通过 React.createRef() 方法创建，然后再在jsx模板中进行使用

* 通过创建的ref对象的current属性拿到DOM节点

  ```react
  export default class App extends Component {
    myref = React.createRef() // 在类中创建一个成员变量
    render() {
      return (
        <div>
          <input type='text' ref={this.myref} />
          <button
            onClick={() => {
              console.log('click')
              //  通过ref对象获取DOM元素
              console.log(this.myref.current.value)
            }}
          >
            添加
          </button>
        </div>
      )
    }
  }
  ```

## 状态

* 像需要渲染在模板的数据，应该保存在状态里，使用普通变量react无法感知到变量变化，也就无法更新DOM

* 变量需要定义在组件类的state对象中

  ```react
  export default class App extends Component {
    state = {
      text: '收藏'
    }
  }
  ```

* 变量不要直接修改，只能通过组件类的setState方法进行修改

  setState方法会导致render函数执行，DOM更新

  ```react
    <button
            onClick={() => {
              this.setState({
                text: '取消'
              })
            }}
          >
  ```

* state也可以定义在constructor中，但是必须要手动调用super以继承父类属性

  ```react
  constructor() {
          super()
          this.state={
              flag:true
          }
      }
  ```

### setState是异步更新的

* 多次调用setState对同一个变量进行同样的更新时，可能会被合并成一次
  * setState如果处在同步逻辑中，会异步更新状态
  * setState如果处在异步逻辑中，会同步更新状态

* setState的第二个参数可以接收一个回调函数，在状态更新后执行

## 列表渲染

* 使用原生es的map函数，将列表中的数据对应成jsx语法
* 可以用Array.slice()方法，不传参，对state中的数组进行深拷贝，创建一个新数组来修改。
* 可以用Array.splice(index,num)方法对数组元素进行删除，从第index个开始，删除num个

## 事件传参

* 使用bind传参

  ```jsx
   <button onClick={this.handleClick.bind(this,index)}>删除-bind</button>
  ```

* 使用箭头函数传参

  ```jsx
  <button onClick={()=>{this.handleClick(index)}}>删除-箭头函数</button>
  ```

## 条件渲染

* 在{}中写三目运算符，用null代表没有元素

  ```jsx
   {this.state.list.length<=0?<div>暂无待办事项</div>:null}
  ```

* 可以使用&&进行逻辑短路，因为所有的jsx语法都可以看成react的createElement函数

  ```jsx
    {this.state.list.length<=0 && <div>暂无待办事项</div>}
  ```

* 使用className动态绑定类名，绑定display:hidden属性

## 富文本展示

* 为DOM元素添加attribute

  ```jsx
  <div dangerouslySetInnerHTML={{__html:'<h2>abc<h2/>'}}></div>
  ```

  容易遭受xss攻击

## 属性

* 用于父组件向子组件传参

* 子组件应该只读的使用组件

* 类组件通过this.props访问各个属性

  函数式组件通过第一个形参props访问

* 使用prop-types库，设置propTypes类属性对属性的类型进行验证

  * 需要单独下载安装，PropTypes中是验证是不是各种类型的方法，直接使用以判断类型

  * 设置类属性propTypes对props进行验证

    ```js
    Navbar.propTypes = {
      title:PropTypes.string,
    }
    ```

    或使用static写在类里头

    ```js
    export default class Navbar extends Component {
         static	propTypes = {
          title:PropTypes.string,
        }
    }
    ```

* 设置defaultProps类属性，来对属性设置默认值。写法同propTypes。

## js的类属性和对象属性

```js
class test {
    a=123
static c='qwer'
}

test.b='abc'
```

* a为对象属性，只能在test实例化后的对象上访问到，无法通过test.a访问

  ```js
  test_obj=new test()
  console.log(test_obj.a)
  ```

* b为类属性，只能通过test.b访问到，无法在test实例化后的对象上访问

* c可以同时通过test.c和在test实例化后的对象上被访问到

## 受控/非受控组件

* 非受控组件：

  ​	比如对于input输入框，通过defaultValue设置其默认值，不将其value绑定为state中的属性，每次value改变时不会被react感知到，不会触发setState等方法。

* 受控组件：

  ​	将input的value与状态中的value变量进行绑定，监听其onChange方法，在input的value改变时，令state中的value变量也进行改变。这样每次input的value改变，react都能感知到。

## 父子组件通信

* 父组件向子组件传参：props
* 子组件向父组件传参：父组件向子组件传入一个回调函数，子组件调用该回调函数。

:star: 在react中，应当尽量多写无状态组件，尽量多写受控组件，尽量少写有状态组件（尽量给子组件设置状态）

## 状态提升

​	子组件通过传入回调函数的形式将自身状态传给父组件，父组件将其设置为自己的状态，并可以通过props传递给其他子组件。

## 发布订阅模式

* 订阅者传入一个自己的回调函数到服务端保存
* 发布者发布时，服务端调用所有订阅者的回调函数

## context状态树传参

跨组件通信，运用了生产者消费者的理念

* 使用`const GlobalContext = React.createContext()`创建GlobalContext 组件

* 使用`<GlobalContext.Provider>`组件在render函数的返回值中包裹所有生产者的DOM元素

  同时在其value attribute讲参数（多个参数则以对象的形式）传递给消费者

  ```jsx
  <GlobalContext.Provider value={{a:'abc',b:'123'}}> 
          <div>context
            <Son/>
          </div>
  </GlobalContext.Provider>
  ```

* 在消费者中，` <GlobalContext.Consumer>`需要包裹一个**回调函数**，唯一参数value为生产者传递的参数

  ```jsx
    render() {
      return (
        <GlobalContext.Consumer>
          {value => {
            console.log(value)
            return <div></div>
          }}
        </GlobalContext.Consumer>
      )
    }
  ```

* 消费者不能直接更改value中的数据，可以通过生产者在value中传入修改数据的回调函数的方式，让消费者调用，对数据进行修改

  ```jsx
  <GlobalContext.Provider
      value={{
          changeA: (value) => { // 修改a的回调函数，这里必须用箭头函数，以保证this指向生产者组件
              this.setState({
                  a: value
              })
          },
              ...this.state
      }}
  >
  ```

## 插槽

* react组件中可以通过props中的children属性`this.props.children`获取到所有放入子组件标签中的的内容
* children中如果传入多个jsx DOM元素，可以以数组进行访问，以实现类似vue具名插槽的效果

## 生命周期

### 初始化

初始化阶段的生命周期：constructor->static getDerivedStateFromProps->render->componentDidMount

* componentWillMount 已弃用，在react16.2版本中由于diff算法更新，加入了fiber技术，以防止在对比过多的DOM元素时浏览器假死，该生命周期不再安全。

#### constructor

可以在constructor阶段进行初始化

#### componentDidMount

* 只会执行一次，在组件挂载完成后。
* 一般在该阶段进行数据请求、订阅函数的调用、添加事件监听、基于DOM元素的初始化

 ### 运行

运行阶段的生命周期：shouldComponentUpdate->render->componentDidUpdate

#### shouldComponentUpdate

* 控制是否执行render函数，返回true会重新渲染，返回false不会渲染

* 用于性能优化

* 此时尚未进行更新，通过this访问的属性为旧状态

* 可以通过传入的参数`(nextProps,nextState)`拿到更新后的状态，用于对比，判断是否需要更新

* 对于有多个属性的浅层对象，可以使用`JSON.stringify`转化成字符串后判断是否相等。

  

#### componentDidUpdate

* DOM更新完毕后执行
* 接收两个参数
  * prevProps：更新之前的属性
  * prevState：更新之前的State

### 销毁

#### componentWillUnmount

* 销毁监听器、计时器

### 新生命周期

#### static getDerivedStateFromProps

* 在初始化以及后续更新（包括自身更新和父向子传参更新）时都会执行

* 调用该方法时，需要在类中已定义state，且需要返回一个对象来更新state
* 参数为`(nextProps,nextState)`，拿到更新后的props和state
* 因为是static方法，因此该方法中的this指向undefined
* 只应进行将props转化为state的操作，不要进行诸如数据请求等异步操作

#### getSnapshotBeforeUpdate

* 可以用于记录DOM状态
* 



## Hooks

### 纯函数和副作用

* 纯函数：

  ​	即作用仅为返回一个值的函数，对于相同的输入输出永远的是相同的。函数的输出与输入值以外的所有其他状态都无关，且除了输出返回值没有其他效果，比如不能有修改全局状态、修改输入值、写入文件等操作。

* 副作用：

  ​	函数多次调用时会重复产生的附加影响。

  ​	即纯函数不应该直接包含产生副作用的操作，只应通过调用其他函数产生副作用。

  ​	react的函数式组件应当都是纯函数（只以返回一个jsx DOM结构为目的），通过调用**React Hooks**来进行副作用操作。

### useState

* 在函数式组件中使用状态
* 传入参数为默认值
* 返回值为一个数组，第一个元素是状态变量，第二个是设置状态的函数（唯一改变该状态的手段）

### useEffect

* 执行副作用，可以进行发送请求等操作

* 传入参数：`useEffect(callback,[依赖数组])`
  * 第二个参数为useEffect的依赖，只有依赖当中的数据有变化，useEffect才会重新执行。如果传入一个空数组，则useEffect只会执行一次（起到类似componentDidMount的效果）
  * 如果不传入依赖，则每次更新时都会执行useEffect
  
* 返回值：可以返回一个函数，这个函数会在组件被销毁时调用一次，进行一些比如定时器/监听器的销毁操作。

* useEffect可以使用多次，多个不相关的副效应应当放在不同的useEffect当中，不应该写在一起。

* useEffect和useLayoutEffect

  推荐优先使用useEffect

  * useEffect会在页面渲染完成后调用
  * useLayoutEffect会在DOM更新后、页面渲染之前被同步的调用，有可能会阻塞页面渲染。
  
* 常见使用场景：

  * 获取数据
  * 事件的监听和订阅
  * 改变DOM
  * 输出日志

### useContext

* 在函数式组件中使用由`<GlobalContext.Consumer>`提供的数据

  `const context=useContext(context对象) // 获取倒context对象`

### useCallback

* 参数传入一个回调函数和依赖数组，会记忆函数（包括传入的参数等），只有在依赖改变时才会更新函数。

  避免函数被不必要的重复创建。

### useMemo

* 传入参数也是callback和依赖数组

* 类似vue中的计算属性，会将callback的**返回值**缓存，当依赖数组发生变化时重新计算

  缓存的时callback的返回值，这也是与useCallback的主要区别

### useRef

* useRef可以在函数式组件中使用Ref，用于获取DOM节点。类似类组件的`React.createRef()`
* 可以用于保存一个变量，在每次函数式组件更新时，保存这个变量的值

### useReducer

* 用于和Redux配合使用

  * 创建reducer和初始状态对象
  * 在**父组件**中使用useReducer创建state和dispatch
  * 通过context将state和dispatch共享给子组件
  * 子组件中根据需要通过dispatch触发各类事件，在reducer中根据type进行分支判断

  ```jsx
  // 在reducer中对状态进行修改
  const reducer = (prevState, action) => {
    console.log(action)
    // 不要直接修改原状态
    const newState = { ...prevState }
    // 通过对type进行分支判断，对状态进行修改
    switch (action.type) {
      case 'decrement':
        // 也可以直接 return {count: prevState.count - 1}
        newState.count--
        break
      case 'increment':
        newState.count++
        break
      default: 
    }
    // 返回新的状态
    return newState
  }
  
  // 对状态进行初始化
  const initialState={
    count:0
  }
  
  // useReducer的第一个参数reducer为状态的处理函数，第二个参数为对状态进行初始化的对象
  const App = () => {
      const [state, dispatch] = useReducer(reducer,initialState)
      return (
          ...
      )
  }
  
  
  // 通过state.属性名 访问state中的属性，
  // dispatch函数传入，通过type属性在reduce中进行分支判断，可以加上其他字段作为参数
  return (
      <div>
        <button onClick={()=>{
          dispatch({
            type:'decrement'
          })
        }}>-</button>
        {state.count}
        <button>+</button>
        </div>
    )
  ```

* 可以与context结合使用，将state和dispatch作为value传递给子组件，以达到与子组件共享状态的目的。
* reducer函数中不能进行异步操作。

### 自定义hooks

* 以`use`开头的函数

* 可以对现有的hooks进行封装，以进行复用

  ​	因为只能在函数式组件或自定义hooks中使用React hooks，因此如果想封装带有hooks的逻辑，就需要写成自定义hooks。

  ​	比如可以对请求一个接口进行封装，在自定义hooks内部使用useEffect请求数据，保存在useState创建的状态中，再返回出来。

  ```jsx
  // 对请求电影列表接口的逻辑进行封装
  function useCinemaList() {
    const [list,setList]=useState([])
    useEffect(()=>{
      axios.get('url').then(res=>setList(res))
    })
    return {list}
  }
  ```

## React路由

 [Declarative routing for React  apps at any scale | React Router](https://reactrouter.com/) 

### 反向代理

​	服务器与服务器之间请求数据没有跨域限制，因此先用反向代理服务器向其他涉及跨域的服务器请求数据，再通过浏览器向反向代理服务器请求数据，以解决跨域问题。

* 在`create-react-app`脚手架中，通过`http-proxy-middleware`中间件进行反向代理

  1. 安装 

     `npm i http-proxy-middleware`

  2. 在src目录下创建`setupProxy.js`文件，文件名不能改

  3. 在文件中进行配置

     ```js
     import { createProxyMiddleware } from "http-proxy-middleware"
     
     export default app=>{
         // 只代理`/api`这一类请求
       app.use('/api',createProxyMiddleware({
         target:'http://localhost:5000',// 要访问的目标地址
         changeOrigin:true
       }))
     }
     
     // ----其他文件中------
     // 后续请求`/api`的接口，会自动补充target的地址，并由反向代理服务器发起请求
     axios.get('/api/xxxxxx').then(res=>{})
     ```

  * 可以添加多个反向代理

* 应该也可以在package.json中配置反向代理

## css模块化

* 在create-react-app脚手架中，可以将css的名称命名为`[文件名].module.css`的形式，会在编译时将类名加上文件名和hash值，防止样式冲突。

* 在引入`[文件名].module.css`的css文件时，需要通过

  ```jsx
  import style from './styleSheet.module.css'
  
  // 通过`style.原类名`的形式拿到新生成的类名
  function app() {
      return (
          {/*title为原类名*/}
      	<div className={style.title}>123aaaa</div>
      )
  }
  ```

## 进度记录：

1. 暂时跳过选项卡、卖座案例
2. setState异步更新，betterScroll案例
3. 受控与非受控的选项卡
4. 暂时跳过54-57的轮播图案例
5. 暂时跳过useReducer
6. 暂时跳过路由，v6已经出了，v5比较老了



