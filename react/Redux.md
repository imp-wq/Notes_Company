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

