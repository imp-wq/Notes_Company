import type { App } from 'vue'

import Widget$1 from './index.vue'

// 注册单个组件的插件
Widget$1.install = (app: App) => {
  app.component(Widget$1.name, Widget$1)
}

export default Widget$1
