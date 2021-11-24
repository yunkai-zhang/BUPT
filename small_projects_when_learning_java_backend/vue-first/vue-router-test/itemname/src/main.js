// 添加router后的main.js内容如下
// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'

// 导入创建的路由配置文件；只需要指定文件夹vue就会自动扫描里面的路由配置。这一步以后不管添加多少路由，都不用变了，只会有路由配置文件内部进行路由增加或修改，
import router from './router'

// 来关闭生产模式下给出的提示
Vue.config.productionTip = false;

new Vue({
  el:"#app",
  // 配置路由。这一步以后不管添加多少路由，都不用变了，只会有路由配置文件内部进行路由增加或修改。
  router,
  components:{App},
  template:'<App/>'
});

