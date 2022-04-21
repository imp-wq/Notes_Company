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

### useState

* 在函数式组件中使用状态
* 传入参数为默认值
* 返回值为一个数组，第一个元素是状态变量，第二个是设置状态的函数

### useEffect









## 进度记录：

1. 暂时跳过选项卡、卖座案例
2. setState异步更新，betterScroll案例
3. 受控与非受控的选项卡



