<template>
  <div>
    <h1>个人信息</h1>
    {{$route.params.id}}
  </div>
</template>
<script>
export default {
  name: "UserProfile",
  //钩子函数,相当于过滤器
  beforeRouteEnter: (to, from, next) => {
    //加载数据
    console.log("进入路由之前")
    //vm是本vue文件的VM对象。“=> {}”是函数的一个简写。
    next(vm => {
      //进入路由之前执行getData方法
      vm.getData()
    });
  },
  beforeRouteLeave: (to, from, next) => {
    console.log("离开路由之前")
    next();
  },
  //axios
  methods: {
    getData: function () {
      this.axios({
        method: 'get',
        //我们浏览器输入这个链接也能读到json文件。注意静态文件一定要放在static下才能访问。
        url: 'http://localhost:8080/static/mock/data.json'
      }).then(function (response) {
        console.log(response)
      })
    }
  }
}
</script>
<style scoped>
</style>
