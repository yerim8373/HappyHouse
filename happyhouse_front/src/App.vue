<template>
  <div id="app" class="min-vh-100">
    <nav-bar />
    <router-view />
    <!-- <footer class="mt-auto">
      <footer-bar />
    </footer> -->
  </div>
</template>

<script>
import NavBar from "@/components/layout/NavBar.vue";
//import FooterBar from "@/components/layout/FooterBar.vue";

export default {
  name: "App",
  components: {
    NavBar,
    //  FooterBar,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  min-height: 100%;
  position: relative;
  /*padding-bottom: 250px;*/
  background-image: url("@/assets/aptment.jpg");
  background-size: 100% 100%;
}

#wrapper {
  height: 70%;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
