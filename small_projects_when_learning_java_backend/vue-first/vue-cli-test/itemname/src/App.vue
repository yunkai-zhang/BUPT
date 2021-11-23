<template>
  <div id="app">
    1
    <Test></Test>
    <img src="./assets/logo.png" />
    <router-view />
  </div>
</template>

<script>
import Test from "./components/test.vue";
export default {
  components: { Test },
  name: "App"
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
