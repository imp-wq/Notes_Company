# React Router

 [Declarative routing for React apps at any scale | React Router](https://reactrouter.com/) 

# V5

### 基本使用

* 安装

* 使用

  ```jsx
  <HashRouter>
      <Switch>
          <Route path='/films' component={Films} />
          <Route path='/cinemas' component={Cinemas} />
          <Route path='/center' component={Center} />
          {/* 加入exact进行精确匹配 */}
          <Redirect exact from='/' to='/films' />
          {/* 所有没被匹配到的，会展示404 */}
          <Route component={NotFound}/>
      </Switch>
  </HashRouter>
  ```

  * 加入HashRouter之后，默认的url会加上`/#/`

  * Route路由放到HashRouter当中

  * Redirect进行重定向，from写`/`和空字符串效果一样

    `from="/"`会进行模糊匹配，即所有以`/`开头的url都会被匹配到

    加入`exact`属性进行精确匹配

  * Switch包裹组件会保证url只匹配其中的一条路由，从前往后依次匹配，匹配到了就不再继续匹配

### 嵌套路由

```jsx
const Films = () => {
  return (
    <div>
      <h2>Film组件</h2>
      <Route path="/films/nowplaying" component={NowPlaying}/>
      <Route path="/films/comingsoon" component={ComingSoon}/>
      <Redirect from="/films" to="/films/nowplaying"/>
    </div>
  )
}
```

* 在Films组件的内部再嵌套写入路由，实现通过路由切换Films组件内部的展示效果
* Films组件中有需要进行嵌套路由的子组件，因此Films在本身在路由中必须进行**模糊匹配**

### 导航

#### 声明式导航

* 使用`<Navlink>`进行导航，Navlink组件需要放到HashRouter当中

  ```jsx
   <div>
            <ul>
              <li>
                <NavLink to='/films' activeClassName={style.active}>电影</NavLink>
              </li>
              <li>
                <NavLink to='/cinemas' activeClassName={style.active}>影院</NavLink>
              </li>
              <li>
                <NavLink to='/center' activeClassName={style.active}>个人中心</NavLink>
              </li>
            </ul>
  </div>
  ```


* 通过设置`activeClassName` attribute来为激活的连接添加类名。不设置的话默认添加`active`类名。
* to属性也可以进行动态拼接

#### 编程式导航

* 通过history对象的方法进行跳转，history对象的方法类似原生js的location对象，有go、push等

  传入`<Route>`标签component attribute的组件，可以通过两种方式拿到history对象：

  * 通过`props.history`，即Route作为父组件，会将history作为props的其中一个属性传给子组件

  * 通过react-router-dom库自带的`useHistory` hooks

    ```jsx
    import {useHistory} from 'react-router-dom'
    const history = useHistory()
    ```

* 在history跳转路由时可以一个对象作为参数，以实现在跳转的同时传参

  ```jsx
  history.push({pathname:`/detail/${id}`,query:{id:123}})
  ```

  * pathname属性指定跳转路由，通过第二个参数query或state进行传参
  * 在路由跳转后的组件中，通过props.location.query/state拿到参数对象
  * 只有在路由跳转之后才能获得参数对象，如果直接访问跳转后页面的url，无法获取参数对象。

### 动态路由

```jsx
<Route path='/detail/:myID' component={Detail}/>
```

* 可以在路径中以`:参数名`的形式，设置动态路由

* 可以在子组件Detail中通过params对象的myID属性拿到动态路由的参数

  类似history，有两种方式拿到params对象：

  * `props.match.params.参数名`

  * 通过useParams

    ```jsx
    const params = useParams()
    const id = params.myID
    ```

### 路由拦截

* 在Route组件中，传入render函数，根据权限进行条件渲染

  ```jsx
  <Route path="/center" render={props=>{return isAuth()?<Center {...props}/>:<Redirect to="login" />}}
  ```

* render函数通过参数props拿到history等参数，解构后传给Center组件，使其能够通过`this.props`访问到history、location等

### 路由模式

* HashRouter和BroswerRouter

  根据使用不同的组件包裹路由模块，选择不同的路由模式。

  BroswerRouter的url中没有`#`，会向后端发起请求。HashRouter不会向后端发起请求。

  可以在引入时取别名以方便切换路由模式

  ```jsx
  import {HashRouter as Router} from 'react-router-dom'
  import {BroswerRouter as Router} from 'react-router-dom'
  ```


### withRouter

* withRouter可以传入一个组件，使其能够获得路由对象，可以访问history、location、match三个对象

  类似路由拦截传入render函数时给子组件传入`...props`的效果

  

