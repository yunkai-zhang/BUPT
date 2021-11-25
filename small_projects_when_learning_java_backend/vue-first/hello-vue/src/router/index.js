//导入vue
import Vue from 'vue';
import VueRouter from 'vue-router';

//导入组件
import Main from "../views/Main";
import Login from "../views/Login";
import NotFound from '../views/NotFound'
//导入主页下的子组件
import UserList from "../views/user/List";
import UserProfile from "../views/user/Profile";

//使用
Vue.use(VueRouter);

//导出
export default new VueRouter({
  mode: 'history',
  routes: [
    //首页，前端url请求/main的时候路由到Main.vue页面
    {
      path: '/main',
      component: Main,
      //  写入子模块
      children: [
        {
          path: '/user/profile/:id',
          name:'UserProfile',
          component: UserProfile
        },
        {
          path: '/user/list/:id',
          name:'UserList',
          component: UserList,
          props: true
        },
      ]
    },
    //登录页
    {
      path: '/login',
      component: Login
    },
    //展示重定向
    {
      path: '/goLogin',
      redirect: '/login'
    },
    {
      path: '*',
      component: NotFound
    }

  ]

})

