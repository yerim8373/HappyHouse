<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col> </b-col>
    </b-row>
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
            src="@/assets/userupdate.png"
            style="width: 340px; height: 70px; margin-top: 1px"
          />
          <hr />
          <b-form class="text-left">
            <b-form-group label="아이디" label-for="id">
              <b-form-input id="id" v-model="user.id" readonly></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호" label-for="password">
              <b-form-input
                type="password"
                id="password"
                v-model="user.password"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group label="Email" label-for="email">
              <b-form-input
                type="email"
                id="email"
                v-model="user.email"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group label="휴대폰 번호" label-for="phone">
              <b-form-input
                type="phone"
                id="phone"
                v-model="user.phone"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름" label-for="name">
              <b-form-input
                type="name"
                id="name"
                v-model="user.name"
                required
              ></b-form-input>
            </b-form-group>
          </b-form>
          <b-button
            type="button"
            style="width: 200px; background-color: #609ac0"
            class="m-1"
            @click="update"
            >수정</b-button
          >
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import { update } from "@/api/user";

const userStore = "userStore";

export default {
  name: "UserModify",
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
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  created() {
    this.user = this.userInfo;
    console.log(this.user);
  },
  methods: {
    update() {
      update(
        this.user,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.movePage();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    movePage() {
      this.$router.push({ name: "mypage" });
    },
  },
};
</script>

<style></style>
