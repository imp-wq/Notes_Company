## TypeScript

## 基础

* 全局安装ts 

  `npm i typescript -g`

* 使用tsc命令编译ts文件

* 产生ts配置文件：

  `tsc --init`

* ts文件修改后自动执行编译：

  `tsc --watch`

* 在ts出现错误时，不进行编译

  `tsc --noEmitOnError --watch`

* 降级编译

  在tsconfig.json的target属性设置为`es5`，即可兼容低版本es

* ts目录：rootDir

* 输出js的目录：outDir



* ts-node：可以使用ts-node命令直接编译运行ts文件

### yarn全局安装ts

​	使用`yarn global add typescript --dev`安装ts后，需要添加环境变量才可使用tsc命令。

* 通过`yarn global dir`查询当前yarn全局安装的目录。
* 将`global/node_modules/.bin`添加至环境变量。该目录中有`tsc.cmd`文件。

### ts-node

* 安装ts-node
* 安装依赖：`yarn global add tslib @types/node`

### 显式类型

* 在变量名后使用`: 类型`
* 对于编译器能够推断出类型时（如变量声明后立即进行初始化），无需显式指定类型注释。
* 但对于已经确定类型的变量，不能将其直接赋值为其他类型，有强类型语言的特性。

### 严格检查标记

这些属性都在tsconfig.json中进行设置：

* strict：默认true，设置为false可以直接兼容普通js，开启后等于开启下面两个配置
* noImplicitAny：默认没启用，启用后也是用来检查未声明的any类型
* strictNullChecks：启用后不允许将null和undefined赋值给其他类型的变量

## 常用类型

* 基元类型：string，number，boolean，注意不要大写，大写的表示内置类型

* 数组：

  * `类型[]`
  * `Array<类型>`：泛型写法

* any类型：用于不知道其类型时，不进行类型检查。可以为它分配任何值。

  注意将变量设置为any类型，不会对其进行任何类型检查。

* 函数：可以指定参数类型注释和返回类型注释

  通常不需要写**返回类型注释**，因为ts会根据return语句自动推断。

  * 函数的参数的上下文类型：

    比如在forEach传入的回调函数中，无需指定参数item的类型，可以通过上下文推断。

* 对象类型：

  `对象名:{属性名:属性类型,...}`

  会校验对象里是否有这些属性，以及属性的类型。

* 可选属性/参数：在函数/对象的参数/属性名后加上`?`，可以设置其为可选参数

  * 可选参数在函数体中，必须使用`?.`运算符调用其方法，以防止其为`undefined`而出现错误。
  
* null和undefined类型

  可以用`x!`断言x不为null和undefined类型

## 枚举类型

不是js原生的类型

## 联合类型

* 通过`|`将多个属性联合起来

  联合类型可以传入多个类型，但在使用时要根据需要进行类型缩小，比如用typeof进行类型判断，数组需要用Array.isArray方法进行判断

  ```ts
  function printId(id:number|string) {
    console.log(id.toUpperCase) // 报错，不能在一个number|string的union类型上使用string的方法
  }
  ```

## 类型别名

* 使用type来给类型定义别名

  ```ts
  type Obj={
    x:number
    y:number
  }
  ```

  

## 接口

* 使用interface进行声明

  ```ts
  interface Obj2 {
    x:number
    y:number
  }
  ```

* 别名vs接口：

  复用：

  * 接口可以通过extends继承其他接口
  * 别名通过&将两个别名进行交叉

  添加字段：

  * 如果多次定义同名接口，可以向其中添加字段。
  * 同名类型不能重复定义

## 类型断言

* 在不知道一个变量的具体类型时，使用类型断言进行类型的缩小
* 使用as或尖括号语法
  * 变量 as 类型
  * <类型>变量

## 字面量类型

* 通过将变量值设置为类型，使得变量只能为几个字符串中的一个

```ts
let str1:123|'right'|'center'
```

* 对于对象中的属性，ts会默认其为变量。如果想将其识别为字面量类型，有两种方式：

  * 类型断言，为属性添加类型断言

  * 为整个对象添加`as const`

    ```ts
    function request(url: string, method: 'GET' | 'POST') {}
    const req = {
      url: 'localhost:8080',
      method: 'GET'
    }
    
    request(req.url, req.method) // 会报错，会将method识别为string类型，而不是'GET'
    ```

    ```ts
    const req = {
      url: 'localhost:8080',
      method: 'GET' as 'GET' // 为属性添加类型断言
    }
    ```

    ```ts
    const req = {
      url: 'localhost:8080',
      method: 'GET'
    } as const // 为整个对象添加类型断言
    ```

## 类型缩小

* typeof类型守卫

  typeof会返回一个字符串，为变量的类型。

  typeof会认为null也是object即，`typeof null === 'object'`为true

* 真值缩小

  可以首先使用&&、if、!!等方式首先排除掉null和undefined，再使用typeof进行判断。

  即在ts中使用boolean+条件语句进行判断时，ts会自动进行类型缩小。

* 等值缩小

  在使用==或===以及!= !==进行等值判断时，ts也会进行类型缩小

  可以直接使用`变量!=null`来过滤null和undefined

* instance of缩小

* 分配缩小

  在对一个联合类型进行赋值时，ts会根据赋值缩小它的类型

* 控制流分析

  在根据typeof进行类型分析，进行if-else判断、return等控制流语句时，ts会自动缩小其类型。

## 类型谓词

类似一个boolean变量，类型谓词只能用来定义方法的返回类型。



 

 