<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card
          class="text-center mt-3"
          style="
            max-width: 40rem;
            border-style: double;
            border-color: #609ac0;
            background: #f3f3f6;
            background: rgba(255, 255, 255, 0.5);
          "
          align="left"
        >
          <img
            src="@/assets/login.png"
            style="width: 180px; height: 70px; margin-top: 1px"
          />
          <hr />
          <br />
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >
            <b-form-group label-for="id">
              <b-icon icon="person-fill"></b-icon>
              <label>아이디</label>
              <b-form-input
                id="id"
                v-model="user.id"
                required
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label-for="password">
              <b-icon icon="lock-fill"></b-icon>
              <label>비밀번호</label>
              <b-form-input
                type="password"
                id="password"
                v-model="user.password"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <a
              type="button"
              variant="info"
              style="
                text-decoration: none;
                float: right;
                margin-right: 10px;
                margin-bottom: 20px;
              "
              >비밀번호 찾기</a
            >
            <a
              type="button"
              variant="info"
              @click="movePage"
              style="
                text-decoration: none;
                float: right;
                margin-right: 10px;
                margin-bottom: 20px;
              "
              >회원가입 |
            </a>
            <b-button
              block
              type="button"
              variant="info"
              class="m-1"
              @click="confirm"
              style="height: 70px; background-color: #609ac0"
              >로그인</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
// import axios from "@/api/http";
import { mapState, mapActions } from "vuex";

const userStore = "userStore";

export default {
  name: "UserLogin",
  data() {
    return {
      user: {
        id: null,
        password: null,
      },
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(userStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      console.log(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "home" });
      }
    },
    movePage() {
      this.$router.push({ name: "register" });
    },
    // loginSubmit() {
    //   let saveData = { id: this.id, password: this.password };

    //   console.log(saveData);

    //   axios.post("/user/login", saveData).then((response) => {
    //     if (response.data != null) {
    //       console.log(response.data);
    //     }
    //   });
    // },
  },
};
</script>

<style></style>
