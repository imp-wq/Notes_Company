# flex布局

## flex布局原理

* 任何一个容器都可以指定为flex布局
* 父元素指定为flex布局以后，子元素的float、clear和vertical-align将失效

## 常见父项属性

 ### flex-direction

* 用于设置主轴方向

* 主轴方向默认水平向右，元素默认是跟着主轴排列的
* 侧轴方向默认水平向下

* 属性值
  * row：默认值，从左到右
  * row-reverse：从右到左
  * column：从上到下
  * column-reverse：从下到上

### justify-content

* 设置主轴上的排列方式，使用前要先确定主轴方向
* 属性值
  * flex-start：默认值，从头部开始排列
  * flex-end：从尾部开始排列
  * center：让子元素居中对齐
  * space-around：平分剩余空间
  * space-between：两边的元素贴边，然后平分剩余空间

* 子元素垂直居中：

  flex-direction设置为column，justify-content设置为center

### flex-wrap

* 默认情况下，项目都排在一条轴线上。如果子元素一行摆不下，flex会修改子元素的大小，强行把所有子元素塞到一行。
* 通过flex-wrap设置子元素换行显示。
* 属性值
  * nowrap：默认值，不换行
  * wrap：换行

### align-items

* 设置侧轴上的子元素排列方式，适用于单行子元素
* 属性值
  * flex-start
  * flex-end
  * center
  * stretch：拉伸，设置该属性时，子元素不能设置高度

### align-content

* 设置侧轴上的子元素排列方式，适用于多行（即设置了换行）子元素

* 属性值
  * flex-start	
  * flex-end
  * center
  * space-around
  * space-between
  * stretch

### flex-flow

是flex-direction和flex-wrap的复合属性。

```css
flex-flow: row wrap;
```

## 常见子项属性

### flex

* flex属性为子项目分配剩余空间，用flex来表示多少份数。

### align-self

* align-self控制子项自己在侧轴上的排列方式，用于让某个项目和其他项目不一样，可覆盖align-items属性

### order

* 定义项目的排列顺序，数值越小越靠前。默认为0。