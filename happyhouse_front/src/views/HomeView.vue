<template>
  <b-container id="bgi" class="bv-example-row">
    <b-row class="mt-5 mb-5" align-v="center" align-h="center"> </b-row>
    <b-img center :src="require('../assets/main.png')" fluid-grow></b-img>
    <b-iconstack font-scale="5" class="mt-5">
      <router-link :to="{ name: 'map' }"
        ><b-icon stacked icon="circle-fill" style="color: #3d769f"></b-icon
      ></router-link>
      <b-icon
        stacked
        icon="search"
        scale="0.5"
        variant="white"
        animation="throb"
      ></b-icon>
    </b-iconstack>
  </b-container>
</template>

<script>
export default {
  name: "HomeView",
  components: {},
};
</script>

<style scoped>
/* #title {
  color: aliceblue;
} */
</style>
