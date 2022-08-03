# JS数据结构与算法

## Jest in TS

* 局部安装

  `yarn add --dev jest`

* 全局安装

  `yarn global add jest`

  全局安装以后才可以使用jest命令行。

* `yarn add --dev babel-jest @babel/core @babel/preset-env`

* `yarn add --dev @babel/preset-typescript`

* :question: 问题：如何让vscode 对于test、expect等函数不报错？

## 数组

* Array.sort()函数会默认将数组按字符串进行排序，即使是`Array<number>`类型的数组。



* 栈=》进制转换
* 队列=》循环队列，击鼓传花
  * 用入列+出列循环队列模拟击鼓传花的位置，用第一个位置代表当前传花的人，出队列。
  * 用java如何实现？
* 双端队列=》回文检查器
  * 利用deque的removeFront和removeBack，比较是否相等，size<1退出循环。



## 链表

## 集合

* in运算符：`'key' in obj`

  如果key property在obj对象或其原型链上，则返回true，否则返回false。

  key应为string或symbol类型。

  这里的in运算符与for in是不一样的。

​		本Set中使用in运算符判断property是否已存在。

* Object.keys(obj)：static方法，用于以数组形式返回一个obj的所有key。有序。

  >The ordering of the properties is the same as that given by looping over the properties of the object manually.

  本Set中使用该数组的长度作为size方法的返回值。

  Object.values(obj)：用于以数组形式返回obj的所有values。

* 使用for in遍历items得到size时，为什么还需要items.hasOwnProperties进行判断？

  >因为对象的原型包含了额外的属性（属性既有继承自 JavaScript 的 Object 类的，也有属于对
  >
  >象自身、未用于数据结构的）。

#### 集合运算

* 交

  直接将2个集合的元素都add到新集合中。

  原生set交集：

  ```js
  const unionAB3 = new Set([...setA3, ...setB3])
  ```

* 并

  取元素较少集合，forEach逐个利用has方法判断另一个集合里有没有，有就add到新集合。

  原生set并集：

  ```js
  const intersectionAB3 = new Set([...setA3].filter(x => !setB.has(x)))
  ```

* 补

  先判断size，如果>直接return false。然后利用every方法逐一判断本集合里的元素另一集合有没有。

## 递归

* 测试当前环境调用栈层数的函数。

* 尾调用优化(tail call optimization)：

  >如果函数内的最后一个操作是调用函数，会通过“跳转指令”（jump）而不是“子程序调用”（subroutine call）来控制。

## 树

### 二叉树BinarySearchTree

* 中序遍历：从小到大，根在中间，遍历的结果=升序排序。

  左，中，右

* 先序遍历：优先于后代节点遍历。

  中，左，右

* 