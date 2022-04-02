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



* 设置鼠标放在柱子上，高亮显示的效果

  ```js
  tooltip:{
              trigger:'axis',
              axisPointer: {
                type:'line',
                z:0,
                lineStyle: { // 设置高亮柱子的样式，宽度和颜色
                  type:'solid', // 要设置type为solid，否则高亮是一段一段的
                  width: 66, 
                  color:'#2D3443'
                }
              }
            },
  ```

  

### 图例 legend

* data数组：用于存放数组的数组
  * name属性：应当与series的name属性一一对应
* 在option配置选项中的legend进行配置
* top/bottom/left/right ：类似绝对定位，指定图例在容器中的位置。可以是具体像素值或容器的百分比。

### 表格 Grid

用于控制图表的内容区域

* top/bottom/left/right ：每个方向上距离DOM容器的距离
* containLabel：是否显示刻度标签。计算距离的时候，是否包含标签。

### x/yAxis

控制xy坐标轴的。饼图不要进行设置。

* type：传入一个字符串，设置该轴显示什么

  一般xAxis设置成`'category'`,yAxis设置成`'value'`，就是正常的柱状图。

* scale：是否从0开始

---

### series 

一个数组，决定显示哪种类型的图表，柱子的样式，标签的显示，以及进行最值、平均值的标注等。

* series是一个数组，每个元素是一个对象，对应图表中的一条线
* 通过type属性指定线的类型，也决定了该对象能配置什么属性
* name：设置名字，需要和legend中data数组中的name属性一一匹配
* data数组：也可以将数据数组放到这里头

#### 柱子的样式

* barWidth：设置柱状图柱子的宽度
* itemStyle对象：为柱子设置样式，比如圆角颜色等

#### 标记

* markPoint对象：进行点标记
  * data数组：进行最大最小值等标记，通过元素对象的type属性进行设置
* markLine对象：进行线标记，标记平均值等
  * data数组：进行平均值的标注
* markArea：可以标记某个区域，比如最小值到平均值。

#### 标签

* label对象：配置标签的属性，如是否永久显示、显示位置等
  * 标签文字的样式，比如color，以及标签文字框的样式，都直接在label的properties中设置

---



## 一些图表

### 气泡图

* 散点图，将每个点的height使用回调函数，返回想要的大小

### 饼图和圆环

* 圆环是通过将饼图的radius半径设置成一个数组实现的

## 颜色渐变

* 在color中传入一个对象进行颜色渐变[Documentation - Apache ECharts](https://echarts.apache.org/zh/option.html#color) 

  type属性决定了渐变的方式，决定了后续配置项有哪些，不能瞎写。

  ```js
  // 线性渐变，前四个参数分别是 x0, y0, x2, y2, 范围从 0 - 1，相当于在图形包围盒中的百分比，如果 globalCoord 为 `true`，则该四个值是绝对的像素位置
  {
    type: 'linear', // linear为线性渐变
    x: 0,
    y: 0,
    x2: 0,
    y2: 1,
    colorStops: [{
        offset: 0, color: 'red' // 0% 处的颜色
    }, {
        offset: 1, color: 'blue' // 100% 处的颜色
    }],
    global: false // 缺省为 false
  }
  ```

## 图表自适应

* 使用window.onresize监听窗口变化事件，调用echarts的实例的resize方法调整图表大小.

  ```js
  window.onresize=ChartInstance.resize
  ```

* 用Onmounted钩子监听window的resize事件，利用echarts容器div原生DOM的offsetWidth和offsetHeight获得实时的宽度和高度，将其乘上一个比例系数，用于图表的字体等，实现自适应。

  

## 事件监听

* 使用图表实例的on方法进行监听

  ```js
  chartInstance.on('事件名',()=>{})
  ```

  

## 在vue中挂在为全局属性

* 在vue3中要使用app.config.globalProperties.$echarts=echarts
* 但在composition API中如何使用global properties是个问题

## 拆分option

可以按这种思路将option拆分成几部分，以易于维护。

多次调用echartInstance.setOption会整合配置，以此来拆分。

* 初始化配置
* 获取数据之后的配置
* 分辨率适配配置



