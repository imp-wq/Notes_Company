# Array高阶函数

### map

​	Array，用于对数组的字段进行匹配。

* 对字段进行处理/重命名，数组长度和顺序都不变的场景。
* 比如对后端传来的数据进行筛选，为了匹配组件库的数据格式进行处理等。

### filter

​	Array，过滤器，会将回调返回为true的项push到新数组。

* 用处和map刚好对应，一个用来改元素的字段，一个用来挑选元素。

### find

​	element，有一个返回true，则返回那个元素。

* 用于根据条件查找元素。

### some

​	boolean，有一个返回true就返回true，否则返回false。

### every

​	boolean，与some相对，所有回调都返回true才返回true，否则返回false。

* some和every可用于根据条件对数组进行判断。

### reduce

​	用于累加。

### forEach

​	void，用于遍历数组元素。

### 

​	