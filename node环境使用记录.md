# Node环境使用记录

​	记录安装node环境的过程。

## 一、安装nvm

* [Releases · coreybutler/nvm-windows · GitHub](https://github.com/coreybutler/nvm-windows/releases)
* [使用 nvm 管理不同版本的 node 与 npm | 菜鸟教程 (runoob.com)](https://www.runoob.com/w3cnote/nvm-manager-node-versions.html)

## 二、安装node

`nvm install lts`

## 三、配置node全局安装目录和缓存目录

* `npm config set prefix "D:\GlobalPackage\npm_global"`
* `npm config set cache "D:\GlobalPackage\npm_cache"`

## 四、npm全局安装yarn

* `npm i -g yarn`

* 配置yarn环境变量。

* 配置yarn安装目录和缓存目录
  * `yarn config set global-folder "路径"`
  * `yarn config set cache-folder "路径"`
  * 使用`yarn global dir`进行查看。

## 五、安装vue-cli

* `yarn global add @vue/cli`
* vue cli添加环境变量。