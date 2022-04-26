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

* 使用`<Navlink>`进行导航，Navlink组件需要放到HashRouter当中

  ```jsx
   <div>
            <ul>
              <li>
                <NavLink to='/films'>电影</NavLink>
              </li>
              <li>
                <NavLink to='/cinemas'>影院</NavLink>
              </li>
              <li>
                <NavLink to='/center'>个人中心</NavLink>
              </li>
            </ul>
  </div>
  ```

  