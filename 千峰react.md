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

  ```react
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

## 列表渲染

* 使用原生es的map函数，将列表中的数据对应成jsx语法
* 

