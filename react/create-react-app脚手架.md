# create-react-app

## 配置路径别名

 [React配置路径别名 - 掘金 (juejin.cn)](https://juejin.cn/post/7049272339283836958) 

* 使用craco插件

* 安装

  `npm install @craco/craco`

* 配置

  在根目录中建立`craco.config.js`文件，进行如下配置

  ```js
  // 根路径 -> craco.config.js
  const path = require('path')
  const resolve = dir => path.resolve(__dirname, dir)
  
  module.exports = {
    webpack: {
      alias: {
         // @映射src路径
        '@': resolve('src'),
        'components': resolve('src/components')
      }
    }
  }
  ```

* 修改package.json中的scripts

  ```js
  "start": "craco start",
  "build": "craco build",
  "test": "craco test",
  ```

  