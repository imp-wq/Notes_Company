## UniApp项目

* u-view：通过this.$u访问u-view对象实例

### 发请求的流程

* 在`./common/utils/request.js`文件中引入axios，并进行timeout、返回码校验等一系列配置
* u-view的$u对象本身有http对象，可以用来发请求。
* 在main.js中为对象挂在上$u对象之后，使用httpInterceptor为$u的http对象添加拦截器
* 在`./common/utils/http.api.js`中，定义各个接口的api
  * $u的get和post哪来的
  * 各种代理服务器

## token

* header通过拦截器添加token字段，如果vuex中的token为空，会随意添加一段

  ```js
  		config.header.Token = vm.$store.state.vuex_token || '476f0bec-fd0a-47d1-8fca-b81bf8ca728e';
  ```

* 如果token为空，http请求成功，会返回9100错误码。

  如果token错误，会出现跨域问题，http请求失败。