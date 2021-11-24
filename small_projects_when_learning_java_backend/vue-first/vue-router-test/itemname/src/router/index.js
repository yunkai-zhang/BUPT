import Vue from'vue'
// 导入路由插件
import Router from 'vue-router'
// 导入自己定义的组件，即vue页面
import Content from '../components/Content'
import Main from '../components/Main'
import Zhang from '../components/Zhang'
// 安装路由
Vue.use(Router);
// 配置路由,new后面的`Router`要和第三行导入的`Router`同名。路由很像spring中的controller，path就像requestmapping
export default new Router({
  routes:[
    {
      //路由路径
      path:'/content',
      //路由名称，随便定义，甚至可以不写
      name:'routerForContent',
      //跳转到组件，与import的组件同名
      component:Content
    },
    {
      //路由路径
      path:'/main',
      //路由名称，随便定义，甚至可以不写
      name:'routerForMain',
      //跳转到组件，与import的组件同名
      component:Main
    },
    {
      //路由路径
      path:'/zhang',
      //路由名称，随便定义，甚至可以不写
      name:'routerForZhang',
      //跳转到组件，与import的组件同名
      component:Zhang
    }
  ]
});
