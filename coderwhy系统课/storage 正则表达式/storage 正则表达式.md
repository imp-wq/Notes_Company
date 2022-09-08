## Storage

* localStorage：本地存储，关闭网页重新打开时内容依然保留。

* sessionStorage：会话存储，关闭掉会话时存储的内容会被清除。

  * 主要用于在本次会话页面相互跳转之间进行数据传递。

* 网页关闭后，sessionStorage会丢失。

  同一页面内跳转到新的链接（比如通过`<a href="xxx" target="_self">`跳转），sessionStorage会保留。

  在新的页面内打开链接（比如通过`<a href="xxx" target="_blank">`跳转），sessionStorage会丢失。

### Storage的属性和方法

​	localStorage和sessionStorage都有这些属性和方法。