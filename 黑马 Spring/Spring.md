# Spring

[Spring | Home](https://spring.io/)

* Spring的作用：
  * 简化开发：Ioc、AoP->事务处理
  * 框架整合：MyBatis, MyBatis-plus, structs

* Spring家族，Spring生态圈。
  * Spring Framework: 底层框架，所有Spring技术全部依赖于该Framework。
  * Spring Boot: 加速开发，简化代码。
  * Spring Cloud: 分布式系统的开发。

* Spring系统架构：
  * Core container: 核心容器，装对象的。
  * AOP: Aspect Oriented Programming, 面向切面编程。
  * Data Access/Integration: 数据访问/集成。
  * Web: Spring MVC
  * Test: 单元测试与集成测试。

## IoC与DI

​	Inversion of Control, 控制权反转。指对象的控制权由程序转移到外部，不在程序中使用new创建对象，由外部提供对象。

* Spring提供了IoC容器，用来当作提供对象的”外部“。
* Bean：由IoC容器管理（负责创建）的对象。
* Dependency Injection: DI, 依赖注入，在容器中建立bean与bean之间依赖关系的过程。
* 