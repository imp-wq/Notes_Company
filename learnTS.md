## learnTS

​	代码文件保存在learn-vite项目`src\components\learnTS.ts`

* 字符串类型enum不能自增长，必须手动初始化。
* 数字枚举也可以和字符串枚举混合，形成异构枚举。

* object类型：表示引用类型，即不能将基本数据类型(number,string,boolean)赋给object类型变量。

  ```js
  let t1:object
  t1={}
  t1=()=>{}
  // 不能将基本数据类型赋给object，以下均报错：
  // t1='asdf'
  // t1=false
  // t1=1234
  ```

* 类型断言：

  * 尖括号
  * as语法

* T[K]可以从一个对象类型T获取其K属性的类型。

* 确定赋值语句：`变量！`，明确表示该变量被赋值

* 可以用`ReadonlyArray<T>`类型来声明一个只读数组

  ```ts
  const arr:ReadonlyArray<string|number>=['123',123,'qwer'
  ```

* 接口的任意属性：

  ```ts
  // 任意属性
  interface f1{
      name:string
      age?:number
      [key:string]:any
  }
  ```

### 泛型

* 泛型接口：接口中有字段类型不能确定，可以使用泛型接口。

  类似java的泛型类，接口中声明泛型，字段使用泛型。

  ```ts
  interface f2<T> {
      ppp:T
  }
  ```

### 泛型工具类：

#### typeof

​	返回一个类型，用于获取泛型的具体类型。

感觉类似java的getclass方法

```ts
const obj:f2<number>={
    ppp:1234
}

type getType=typeof obj.ppp // getType=number
```

#### keyof

​	返回一个类型，返回一个类型的所有键，以联合类型的形式返回。

#### in

​	用于遍历枚举类，生成一个新的类型。

```ts
enum Str {
    str1='abcd',
    str2='qwer',
    str3='ab12'
}

type t3={
    [key in Str]:number
}

// 等价于
// type t3 = {
//     abcd: number;
//     qwer: number;
//     ab12: number;
// }
```

#### extends

​	泛型中使用extends：与java一样，指定T必须为某个类型的子类。

```ts
type t2={
    a:string
    b:number
    c:boolean
}

// 这里泛型函数与java类似。泛型声明，参数类型，返回类型。
function fun2<T extends t2>(arg:T):void{
    console.log(arg)
}

// 这里函数参数必须包含a,b,c这3个字段。
fun2({a:'123',b:123,c:false,d:'qwer'})
```

#### Partial

​	返回一个类型，将某个类型的属性全部变为可选(?)。

```ts
type t4=Partial<t2> // 即t4字段与t2一致，但均为可选。
```

使用Partial等价于：

```ts
type Partial<T>={
    [P in keyof T]?:T[P]
}
```

#### Pick

​	从一个联合类型中选出一些类型。

#### Parameters

​	返回一个元组类型，获得一个元组的类型，可用于获取函数的参数。

### 条件类型

​	三目运算符?可以用于类型，起到if-else的作用。

​	条件类型中可以使用infer关键字声明类型。

```ts
type EE = E extends Str ? t3 : t4
```





