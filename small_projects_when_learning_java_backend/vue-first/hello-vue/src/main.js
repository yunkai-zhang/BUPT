// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.

// 导入Vue系列组件
import Vue from 'vue'
import App from './App'
//这里的import router的router不能大写，包括后面的use(router)和new Vue中的router都得一致小写，否则会报错！！
import router from "./router"

//导入ElementUI系列组件。组件名如ElementUI一般首字母大写。
import ElementUI from 'element-ui'
//导入css需要saas编辑器，所以我们之前初始化项目的时候就安装了saas编辑器
import 'element-ui/lib/theme-chalk/index.css'

//使用自定义的，或者导入的组件
Vue.use(router)
Vue.use(ElementUI)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render:h=>h(App)//ElementUI配置的，官方这么写的，我们必须这么配才能配置好elementUI；elementUI绑定App.vue。
})
