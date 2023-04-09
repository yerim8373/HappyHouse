<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col></b-col>
      <b-col
        cols="8"
        style="
          max-width: 40rem;
          border-style: double;
          border-color: #609ac0;
          background: #f3f3f6;
          background: rgba(255, 255, 255, 0.5);
        "
      >
        <div class="text-center m-5" align="left">
          <img
            src="@/assets/mypage.png"
            style="width: 250px; height: 70px; margin-top: 1px"
          />
          <hr />
          <b-container class="mt-4">
            <b-row>
              <b-col>
                <b-icon
                  icon="person"
                  class="rounded-circle p-2"
                  variant="light"
                  font-scale="7"
                  style="background-color: #609ac0"
                ></b-icon>
              </b-col>
            </b-row>
            <br /><br />
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">아이디</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.id }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <br />
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이름</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.name }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <br />
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이메일</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.email }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
          </b-container>
          <hr class="my-4" />
          <div>
            <b-button
              type="button"
              class="m-1"
              @click="update"
              style="width: 200px; background-color: #609ac0"
              >정보수정</b-button
            >
            <b-button
              type="button"
              variant="info"
              class="m-1"
              @click="userdelete"
              style="width: 200px; background-color: #609ac0"
              >회원탈퇴</b-button
            >
          </div>
        </div>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import { deleteUser } from "@/api/user";

const userStore = "userStore";

export default {
  name: "UserInfo",
  components: {},
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    update() {
      this.$router.push({ name: "update" });
    },
    userdelete() {
      deleteUser(
        this.userInfo.id,
        ({ data }) => {
          let msg = "탈퇴 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "탈퇴가 완료되었습니다.";
          }
          alert(msg);
          this.onClickLogout();
          this.movePage();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    movePage() {
      this.$router.push({ name: "home" });
    },
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
