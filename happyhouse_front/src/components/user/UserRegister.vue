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
            src="@/assets/userregist.png"
            style="width: 250px; height: 70px; margin-top: 1px"
          />
          <hr />
          <br />
          <b-form class="text-left">
            <b-form-group label-for="id">
              <b-icon icon="person-fill" style="color: #589ebb"></b-icon>
              <label>아이디</label>
              <b-form-input
                id="id"
                v-model="user.id"
                required
                placeholder="아이디 입력...."
              ></b-form-input>
            </b-form-group>
            <b-form-group label-for="password">
              <b-icon icon="lock-fill" style="color: #589ebb"></b-icon>
              <label>비밀번호</label>
              <b-form-input
                type="password"
                id="password"
                v-model="user.password"
                required
                placeholder="비밀번호 입력...."
              ></b-form-input>
            </b-form-group>
            <b-form-group label-for="email">
              <b-icon icon="envelope-fill" style="color: #589ebb"></b-icon>
              <label> Email </label>
              <b-form-input
                type="email"
                id="email"
                v-model="user.email"
                required
                placeholder="이메일 입력...."
              ></b-form-input>
            </b-form-group>
            <b-form-group label-for="phone">
              <b-icon icon="telephone-fill" style="color: #589ebb"></b-icon>
              <label> 휴대폰 번호 </label>
              <b-form-input
                type="phone"
                id="phone"
                v-model="user.phone"
                required
                placeholder="전화번호 입력...."
              ></b-form-input>
            </b-form-group>
            <b-form-group label-for="name">
              <b-icon icon="person-fill" style="color: #589ebb"></b-icon>
              <label> 이름 </label>
              <b-form-input
                type="name"
                id="name"
                v-model="user.name"
                required
                placeholder="이름 입력...."
              ></b-form-input>
            </b-form-group>
            <br />
            <b-button
              block
              type="button"
              variant="info"
              class="m-1"
              @click="register"
              style="height: 70px; background-color: #609ac0"
              >회원가입</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { register } from "@/api/user";

export default {
  name: "UserRegister",
  data() {
    return {
      user: {
        id: null,
        password: null,
        email: null,
        phone: null,
        name: null,
      },
    };
  },
  methods: {
    register() {
      register(
        this.user,
        ({ data }) => {
          let msg = "등록이 완료되었습니다.";
          if (data === "success") {
            msg = "등록 처리시 문제가 발생했습니다.";
          }
          alert(msg);
          this.movePage();
        },
        (error) => {
          console.log(error);
        }
      );
      console.log(this.user);
    },
    movePage() {
      this.$router.push({ name: "home" });
    },
  },
};
</script>

<style></style>
