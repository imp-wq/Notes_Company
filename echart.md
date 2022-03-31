# Echart

官网

 [Apache ECharts](https://echarts.apache.org/zh/index.html) 

## 安装和引入

* 安装 `npm install echarts`

* 引入

  ```js
  import * as echarts from 'echarts'
  
  Vue.prototype.$echarts = echarts // 在Vue2中可以选择挂在在Vue实例上
  ```

## 配置对象

可以在官网的配置项手册中查看 [Documentation - Apache ECharts](https://echarts.apache.org/zh/option.html#title)  

* toolbox：工具箱，用于将图表保存为图片等
* 

## 提示信息 tooltip

### 触发方式 trigger

* item：适用于柱状图
* axis：适用于散点图等，鼠标放在坐标轴上就会触发

### 图例 legend

* 在option配置选项中的legend进行配置
* top/bottom/left/right ：类似绝对定位，指定图例在容器中的位置。可以是具体像素值或容器的百分比。
* name数组：设置有那些列，

### 表格 Grid

用于控制图表的内容区域

* top/bottom/left/right ：每个方向上距离DOM容器的距离
* containLabel：是否显示刻度标签

### x/yAxis

控制xy坐标轴的。饼图不要进行设置。

* type：传入一个字符串，设置该轴显示什么

  一般xAxis设置成`'category'`,yAxis设置成`'value'`，就是正常的柱状图。

* scale：是否从0开始

### series 

决定显示哪种类型的图表，以及进行最值、平均值的标注等。

* series是一个数组，每个元素是一个对象，对应图表中的一条线
* 通过type属性指定线的类型，也决定了该对象能配置什么属性
* name：设置名字，如果和legend中的name数组一个效果，设置一个即可



* markPoint对象：进行点标记
  * data数组：进行最大最小值等标记，通过元素对象的type属性进行设置
* markLine对象：进行线标记，标记平均值等
  * data数组：进行平均值的标注
* label对象：配置标签的属性，如是否永久显示、显示位置等

* markArea：可以标记某个区域，比如最小值到平均值。

## 一些图表

### 气泡图

* 散点图，将每个点的height使用回调函数，返回想要的大小

### 饼图和圆环

* 圆环是通过将饼图的radius半径设置成一个数组实现的

## 图表自适应

* 使用window.onresize监听窗口变化事件，调用echarts的实例的resize方法调整图表大小.

  ```js
  window.onresize=mCharts.resize
  ```

  





