# Uni-App

跨平台解决方案

* 单位：upx，可以根据屏幕进行自适应，一个屏幕宽度就是750upx

  动态绑定不支持upx

* 组件标签使用uni-app规范，接近小程序规范

## 全局配置

写在page.json文件中。

* pages：设置路由
  * path：路由
  * style：为页面单独设置样式，会覆盖掉全局样式。
* globalStyle：设置背景色，导航栏颜色，标题等等
  * navigationBarBackgroundColor：设置导航栏默认的背景颜色。

* tabBar
  * list：指定列表
  * 最少2个，最多5个
* condition：启动模式列表。开发模式下用于直达页面的，同时可以进行传参。

## 内置组件

### text

* 用于包裹文本，默认放置在一行，类似`<span>`
* decode attribute：设置是否对html中的一些字符进行解码，比如&amp对应&

### view

* 类似div，容器
* hover-class attribute：指定按下去的样式类