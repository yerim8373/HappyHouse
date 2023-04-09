<template>
  <b-navbar toggleable="lg" type="dark">
    <b-navbar-brand href="#">
      <router-link to="/">
        <img
          src="@/assets/logo3.png"
          style="width: 130px; height: 60px"
        /> </router-link
    ></b-navbar-brand>

    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav>
        <b-nav-item href="#">
          <router-link :to="{ name: 'map' }"
            ><img
              src="@/assets/map.png"
              style="width: 150px; height: 33px" /></router-link
        ></b-nav-item>
        <b-nav-item href="#"
          ><router-link :to="{ name: 'board' }" class="link"
            ><img
              src="@/assets/commu.png"
              style="
                width: 100px;
                height: 30px;
                margin-top: 1px;
              " /></router-link
        ></b-nav-item>
      </b-navbar-nav>
    </b-collapse>

    <b-navbar-nav class="ml-auto" v-if="userInfo">
      <b-nav-item class="align-self-center">
        <!-- <b-avatar variant="dark">
        </b-avatar> -->
        <div style="color: #609ac0">
          {{ userInfo.name }}({{ userInfo.id }})님 환영합니다.
        </div></b-nav-item
      >
      <b-nav-item class="align-self-center"
        ><router-link :to="{ name: 'mypage' }" class="link align-self-center"
          ><img
            src="@/assets/mypage.png"
            style="width: 95px; height: 30px" /></router-link
      ></b-nav-item>
      <b-nav-item class="align-self-center"
        ><router-link :to="{ name: 'favorite' }" class="link align-self-center"
          ><img
            src="@/assets/like.png"
            style="width: 77px; height: 26px" /></router-link
      ></b-nav-item>
      <b-nav-item class="align-self-center"
        ><router-link :to="{ name: 'message' }" class="link align-self-center"
          ><img
            src="@/assets/msg.png"
            style="width: 45px; height: 30px" /></router-link
      ></b-nav-item>
      <b-nav-item class="link align-self-center" @click.prevent="onClickLogout"
        ><img src="@/assets/logout.png" style="width: 70px; height: 26px"
      /></b-nav-item>
    </b-navbar-nav>
    <b-navbar-nav class="ml-auto" v-else>
      <b-nav-item-dropdown right>
        <template #button-content>
          <b-icon icon="people" font-scale="2"></b-icon>
        </template>
        <b-dropdown-item href="#"
          ><router-link :to="{ name: 'register' }" class="link"
            ><b-icon icon="person-circle"></b-icon> 회원가입</router-link
          ></b-dropdown-item
        >
        <b-dropdown-item href="#"
          ><router-link :to="{ name: 'login' }" class="link"
            ><b-icon icon="key"></b-icon> 로그인</router-link
          ></b-dropdown-item
        >
      </b-nav-item-dropdown>
    </b-navbar-nav>
  </b-navbar>
</template>

<script>
import { mapState, mapMutations } from "vuex";

const userStore = "userStore";

export default {
  name: "NavBar",
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
  },
};
</script>

<style></style>
