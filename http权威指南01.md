# HTTP权威指南 01

## 一、XXXX

* MIME类型：Multipurpose Internet Mail Extension，多用于因特网邮件扩展，来源于邮件系统，用于标记多媒体内容。
  * web服务器会为所有HTTP对象数据附加一个MIME类型，当浏览器从服务器取回一个对象时，会查看MIME类型，以此得知如何处理这个对象。
  * MIME组成：`主要对象类型/特定子类型`
    * HTML：text/html
    * ASCII文本：text/plain
    * JPEG图片：image/jpeg
    * GIF图片：image/gif
    * ...

* 每个web服务器资源有一个名字，用Uniform Resource Identifier,URI来标识。

  URI有2中形式：URL和URN，现在几乎所有URI都是URL。

  * URL：
    * scheme：方案，即协议类型
    * 服务器地址
    * 服务器的资源

  * URN：作为特定唯一名称使用，与资源目前的所在地无关，比如用于internet标准文档等。目前处于实验阶段。

* 事务：
  * 一个HTTP事务由一条请求和一个响应组成，通过格式化的数据HTTP 报文(message)进行。
  * HTTP method：常见的由GET PUT DELETE POST HEAD。
  * 状态码

* 报文：http报文由字符串组成，是纯文本而不是二进制代码，因此方便读写。

  报文包含3个部分：

  * 起始行
  * 首部字段：以`:`分隔的key-value，以一个空行结束。
  * 主体：起始行和首部都是文本且结构化的，而主体可以包含任何如二进制等格式的数据。

* TCP/IP：

  * TCP/IP为http提供了：

    * 无差错数据传输

    * 按序传输

    * 未分段数据流，允许任意时刻数据以任意尺寸发送。

  * HTTP发送报文之前，需要先根据URL，在服务器和客户端之间建立TCP/IP连接。

    * 通过URL，得知服务器的IP地址，和特定软件的TCP端口号。
    * http默认端口号80
    * IP地址可以通过DNS由域名转换得到。

<img src="D:\wzy\Note\noteimages\http权威指南-http通信过程.PNG" style="zoom:50%;" />

### telnet

​	telnet程序基于http，可以将键盘连接到某个目标tcp端口，并将该端口的输出会送到显示屏。

* telnet可以模拟http客户端，但不能作为服务器使用。
* netcat功能类似，但是更加灵活。
* 