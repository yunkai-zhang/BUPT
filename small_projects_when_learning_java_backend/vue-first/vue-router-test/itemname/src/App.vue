<template>
  <div id="app">
    <h1>欢迎来到vue-router测试页面</h1>
    <!--
      router-link：默认会被渲染成一个<a>标签，to属性为指定链接。
      router-view：用于渲染路由匹配到的组件。当你的路由path 与访问的地址相符时，会将指定的组件替换该 router-view
    -->
    <router-link to="/main">首页</router-link>
    <router-link to="/content">内容</router-link>
    <router-link to="/zhang">张</router-link>
    <router-view></router-view>
  </div>
</template>

<script>
export default{
  name:'App'
}
</script>
<style></style>
