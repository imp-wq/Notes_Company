### 三栏切换

* 切换使用u-view的u-tabs组件
* tab栏，几个列表之间切换->多维数组retList，根据tab栏切换v-for渲染的数组下标
*  输入框有内容时,切换后需要立即进行查询

### 请求

* 保存请求的参数也需要使用多维数组params，下标与保存列表数据的retList一一对应

  params需要为每个栏单独保存这些参数：

  * pageNums页码
  * loadStatus加载更多动画

* 需要在发请求的函数中进行分支判断，根据当前点击的分支，将对应的params数组项添加到axios的参数字段，保存到对应的retList对应项中

* 每次请求要单独判断，是否搜索栏有改动。如果有，需要清空retList对应的数据列表

* 每次请求都要带上搜索栏的数据，以便在搜索之后的下拉刷新里，也是在使用搜索功能

### 现存问题

1. 三个滚动条怎么搞