# JSP

## 导入

* jsp也属于servlet提供的包，因此scope要设置为provided。

## 原理

* JSP 在浏览器访问时会被Tomcat转换为一个servlet，因此JSP本质上就是一个servlet。
* 可以

## 基本使用

* JSP=HTML+Java
* 在html中，在`<% ... %>`标签中，可以直接写java语句。

### jsp脚本分类

* `<% ... %>`：直接放到_jspService()方法中
* `<%= ... %>`：内容会作为out.print()的参数，直接打印到html页面上。
* `<%! ... %>`：内容会放到_jspService()方法外，被类直接包裹。

### EL表达式

* jsp中可以使用`${变量名}`的方式，获取request域对象中转发的变量。
* java web中4大域对象
  * page：当前页面有效
  * request：当前请求有效
  * session：当前会话有效
  * application：当前应用有效
* el表达式会根据变量名从page向上寻找，直到application域或找到对象位置。

### JSTL标签

* 用来取代html页面上java的逻辑判断。
* 导入坐标：jstl和taglibs
* 在页面中引用
* 跳过跳过



