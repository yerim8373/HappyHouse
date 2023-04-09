<template>
  <b-container class="bv-example-row mt-3">
    <img
      src="@/assets/msg.png"
      style="width: 120px; height: 70px; margin-top: 1px"
    />
    <hr />
    <br />
    <b-row class="mb-1">
      <b-col style="text-align: left">
        <b-form @submit="onSubmit" @reset="onReset">
          <b-form-group
            id="title-group"
            label="제목"
            label-for="title"
            label-cols-sm="4"
            label-cols-lg="3"
            content-cols-sm
            content-cols-lg="7"
            description="제목를 입력하세요."
          >
            <b-form-input
              id="title"
              v-model="message.title"
              type="text"
              required
              placeholder="제목 입력..."
            ></b-form-input>
          </b-form-group>

          <b-form-group
            id="receiver-group"
            label="받는이"
            label-for="receiver"
            label-cols-sm="4"
            label-cols-lg="3"
            content-cols-sm
            content-cols-lg="7"
          >
            <b-form-input
              id="receiver"
              v-model="message.receiver"
              type="text"
              required
              placeholder="받는이 입력..."
            ></b-form-input>
          </b-form-group>

          <b-form-group
            id="content-group"
            label="내용"
            label-for="content"
            label-cols-sm="4"
            label-cols-lg="3"
            content-cols-sm
            content-cols-lg="7"
          >
            <b-form-textarea
              id="content"
              v-model="message.content"
              placeholder="내용 입력..."
              rows="10"
              max-rows="15"
            ></b-form-textarea>
          </b-form-group>
          <br />
          <div align="center">
            <b-button
              type="submit"
              class="m-1"
              style="width: 200px; background-color: #609ac0"
              >보내기</b-button
            >
            <b-button
              type="reset"
              class="m-1"
              style="width: 200px; background-color: #609ac0"
              >취소</b-button
            >
          </div>
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { sendMessage } from "@/api/message";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "MessageSend",
  data() {
    return {
      message: {
        sender: "",
        receiver: "",
        title: "",
        content: "",
      },
      isUserid: false,
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  created() {
    this.message.sender = this.userInfo.id;
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.message.title &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.title.focus());
      err &&
        !this.message.receiver &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.receiver.focus());
      err &&
        !this.message.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else this.registMessage();
    },
    onReset(event) {
      event.preventDefault();
      this.message.receiver = "";
      this.message.subject = "";
      this.message.content = "";
      this.$router.push({ name: "messageList" });
    },
    registMessage() {
      sendMessage(
        {
          sender: this.message.sender,
          receiver: this.message.receiver,
          title: this.message.title,
          content: this.message.content,
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "messageList" });
    },
  },
};
</script>

<style></style>
