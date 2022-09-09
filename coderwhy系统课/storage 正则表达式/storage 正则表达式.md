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
    * match
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

  