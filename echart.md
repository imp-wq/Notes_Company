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

### 表格 Grid

用于控制图表的内容区域

* top/bottom/left/right ：每个方向上距离DOM容器的距离
* containLabel：是否显示刻度标签

### x/yAxis

控制xy坐标轴的。饼图不要进行设置。

* type：传入一个字符串，设置该轴显示什么

  一般xAxis设置成`'category'`,yAxis设置成`'value'`，就是正常的柱状图。

  

