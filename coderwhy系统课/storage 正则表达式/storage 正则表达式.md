## Storage

* localStorage：本地存储，关闭网页重新打开时内容依然保留。

* sessionStorage：会话存储，关闭掉会话时存储的内容会被清除。

  * 主要用于在本次会话页面相互跳转之间进行数据传递。

* 网页关闭后，sessionStorage会丢失。

  同一页面内跳转到新的链接（比如通过`<a href="xxx" target="_self">`跳转），sessionStorage会保留。

  在新的页面内打开链接（比如通过`<a href="xxx" target="_blank">`跳转），sessionStorage会丢失。

### Storage的属性和方法

* Storage interface：是对Web storage API的一种实现，localStorage和sessionStorage都实现了该接口。

​	localStorage和sessionStorage都有这些属性和方法。

方法：

* setItem：注意，存储的内容都会被转成字符串进行存储。因此对象类型不能直接存储，需要使用JSON.stringify进行转化。
* getItem
* removeItem：通过key移除item。
* clear()：清空storage。
* key(index)：通过index获取key。

属性：

* length：获取存了多少个item。

### storage工具封装

* 在setItem时直接JSON.stringify，以便对对象进行转化。
* getItem时直接进行JSON.parse，但parse的对象如果为undefined会报错，因此需要判空处理。
* 封装成类的好处：localStorage和sessionStorage可以复用相同的逻辑。

## 正则表达式

regular expression，简写为regexp。

* 正则表达式使用单个字符串描述、匹配一系列匹配某个句法规则的字符串，即，用于进行字符串匹配的。

### 修饰符

* js中使用RegExp类来创建正则表达式，字面量形式为`/regExp/`。

  * RegExp(pattern,flag)，第二个参数flags传入修饰符。一些修饰符：

    * i：ignore，忽略大小写。
    * g：global，全局搜索，如果不加g只会返回第一个搜索的结果。
    * m：multiple，多行匹配。

    ```js
     const reg = new RegExp('abc', 'ig')
    // 等价于 
     const reg2 = /abc/ig
    ```

* pattern：即正则表达式匹配规则。

### 使用正则的函数

* 正则的使用：

  一般来讲test和match方法是用的最多的。

  * 直接使用正则的RegExp的实例方法。
    * exec 
    * test：返回boolean类型，检验字符串是否满足正则规定的规则。
  * 字符串中可以使用正则表达式的方法。
    * match：
      * 无g修饰符：返回匹配到的第一个结果的详情。
      * 有g修饰符：返回一个数组，包含所有匹配的结果（不是详情）。
    * replace：根据正则匹配替换字符串。
    * matchAll：传入的regular expression必须加g修饰符，返回一个迭代器，用于获取匹配到的字符串。
    * search：返回匹配的索引。
    * split：也可以用正则表达式分割子串。

* 正则匹配符：

  * `/^abc$/`：必须只能是单独的abc，不能是包含的abc。（应该是类似字符串的===）

### 字符类

​	Charactor classes，regular expression中的特殊符号，用于匹配特定集中的符号。

​	单独写只会匹配一个字符。

* \d：digit，匹配数字。[0-9]

  * `\d`匹配下一个数字，`\d+`才会匹配一个或多个数字。

* \s：space，匹配空格，包含制表符\t,换行符\n,\r以及一些少数稀有字符。

* \w：word，匹配单字字符、数字、下划线。[a-zA-Z0-9_]

* `.`：匹配换行符之外的任意字符。

  * `/\(.*\)/`：匹配小括号中的所有内容。

* 反向类Inverse classes：

  跟原先反向匹配的类，匹配'非xxx'类型的字符。

  * \D：非数字
  * \S：非空格
  * \W：非单字字符

### 锚点

anchor

* `^`：匹配字符串开头，同字符串的startWith方法。
* `$`：匹配字符串结尾。

### 词边界

word boundary

* \b：词边界，用来代表单词的边界，即边界外不能是可以被\w匹配的字符。

  比如`\bname\b`，即只会匹配单独的name，不会匹配其他单词中包含的name。


### 集合和范围

* 集合Sets：`[...]`方括号中的字符或字符类表示“给定字符中的任意一个”。
* 范围Ranges：在集合中可以包含字符范围，比如`[a-z],[0-5],[0-9A-Z]`。

* 排除范围：`[^...]`^表示排除范围，比如`[0-9]->\d,[^0-9]->\D`。

### 量词Quantifiers

​	量词用来限制每个需要被匹配的字符对应的个数，有这么几种表示方式：

* {数字}：确切的个数{5}，`,`分隔表示范围{3,5}。

* 缩写：

  * +：一个或多个，等价于{1,}
  * ?：可选，0个或1个，等价于{0,1}
  * *：0个或多个，{0,}
  * 

* 注意不要加多余的空格。

* eg：匹配html标签：

  * 标签必须以数字开头。
  * 之后的可以是数字（h2）、字母，也可以直接结束（如p标签）

  ```js
  const htmlElement = '<div><p>哈哈哈哈</p><span>嘻嘻嘻</span></div>'
  const tagPattern = /\<\/?[a-z][a-z0-9]*\>/ig
  console.log(htmlElement.match(tagPattern))
  ```

### 贪婪模式和惰性模型

*和+等量词的匹配默认为贪婪模式greedy，会匹配尽可能多的字符。通过在量词后面加上一个?开启惰性lazy模式。

* 默认的贪婪模式会在查找匹配到的内容后继续向后查找，直到最后一个匹配的内容。

  惰性模式获取到对应的内容后，就不再向后匹配。

* 比如匹配多个书名时，`/《.+》/g`会匹配第一个《和最后一个》之间所有的字符。而开启惰性模式之后`/《.+?》/g`，可以单独匹配到每一个书名。

* 一种应用：匹配聊天中的表情文本，类似[doge]。

### 捕获组capturing group

（）括号括起来的表示捕获组

* 捕获组在详情中会显示，在使用g的match方法中无效，需要matchAll方法。
* 允许通过捕获组将匹配的一部分作为结果数组中的单独项，同时会将括号视为整体。

* 命名分组：`(?<分组名>匹配内容)`通过在括号分组内，括号开始后立即加上`?<分组名>`来为分组命名，命名后的分组会放到结果的groups字段中。

* 非捕获组：`<?:匹配内容>`通过在分组开头添加`?:`表示非捕获组，即只将括号当成一个整体，不希望出现在匹配结果中。

* |：or运算符，表示或。

  与Sets的区别主要在于`[]`内只能是单个字符，|运算符可以连接多个字符。

### 案例：歌词解析

### 案例：日期格式化

