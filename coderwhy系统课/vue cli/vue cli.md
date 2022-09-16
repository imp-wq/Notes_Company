* vue.config.js：再configureWebpack属性中对webpack进行配置。
* jsconfig.json/tsconfig.json：用于告知vscode一些配置，从而让vscode给出更好的提示。
  * 比如其中配置的path选项，可以让vscode对路径别名更好的提示。
  * target：最终打包出的代码。
  * module：模块化规范，exnext表示es最新的版本。
  * lib：对当前项目用到的库进行提示。
    * esnext：最新的es语法提示。
    * dom：dom操作的提示。
    * scripthost：浏览器js的宿主环境。



## vue-runtime与vue.esm-bundler

* 从字符串形式template->VNode：由vue源码完成，因此需要compile代码。
* .vue文件中的template->VNode：由webpack调用vue-loader加载，加载时vue-loader会对template中的内容进行转化。因此只需要vue-runtime。

## vue插件

* vetur：主要用于vue2，对vue3的支持不是很好。
* volar：vue3逐渐从vetur转向volar插件。

## vscode snippet生成器网站

[snippet generator (snippet-generator.app)](https://snippet-generator.app/)

## npm init

* npm init vue@latest的功能：
  1. 安装本地工具create-vue。
  2. 通过create-vue工具创建vue项目。创建出的项目的是基于vite的。

## .vscode->extension文件

.vscode是vscode独立的配置文件。

* extension文件中记录了推荐安装的vscode插件。

