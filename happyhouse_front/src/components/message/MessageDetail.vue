<template>
  <b-container class="bv-example-row mt-3">
    <img
      src="@/assets/msg.png"
      style="width: 120px; height: 70px; margin-top: 1px"
    />
    <div
      style="
        border-style: double;
        border-color: #609ac0;
        background: #f3f3f6;
        background: rgba(255, 255, 255, 0.7);
        margin-top: 20px;
        padding: 20px;
      "
    >
      <b-row class="mb-1">
        <b-col class="text-left">
          <div>
            <h3 style="display: inline; margin: 10px;t">
              {{ message.title }}
            </h3>
            <div class="text-right">
              <span>수신자 {{ message.receiver }} &nbsp; | &nbsp;</span>
              <span>발신자 {{ message.sender }} &nbsp; | &nbsp;</span>
              <span>날짜 {{ message.regtime }}</span>
            </div>
          </div>
          <hr />
          <b-textarea plaintext :value="msg" rows="15"></b-textarea>
        </b-col>
      </b-row>
      <b-row class="mb-1">
        <b-col class="text-right">
          <hr />
          <b-icon icon="trash" style="color: #589ebb"></b-icon>
          <a size="sm" @click="deleteMessage" style="text-decoration: none"
            >삭제</a
          >
          <hr />
        </b-col>
      </b-row>
      <b-row>
        <b-col class="text-align">
          <b-button
            style="width: 200px; background-color: #609ac0"
            class="m-1"
            @click="listMessage"
            >목록</b-button
          >
        </b-col>
      </b-row>
    </div>
  </b-container>
</template>

<script>
import {
  readMessage,
  deleteRecvMessage,
  deleteSendMessage,
} from "@/api/message";
import { mapState } from "vuex";

const messageStore = "messageStore";
const userStore = "userStore";

export default {
  name: "MessageDetail",
  data() {
    return {
      message: {},
    };
  },
  computed: {
    msg() {
      if (this.message.content)
        return this.message.content.split("\n").join("<br>");
      return "";
    },
    ...mapState(messageStore, ["isOption"]),
    ...mapState(userStore, ["userInfo"]),
  },
  created() {
    // console.log(this.$route.params);
    // console.log(this.$router.params.messageno);
    console.log(this.isOption);
    readMessage(
      this.$route.params.messageno,
      this.userInfo.id,
      (response) => {
        this.message = response.data;
        console.log(this.message);
      },
      (error) => {
        console.log("에러발생", error);
      }
    );
  },
  methods: {
    listMessage() {
      this.$router.push({ name: "messageList" });
    },
    deleteMessage() {
      if (confirm("정말로 삭제?")) {
        if (this.isOption) {
          deleteSendMessage(this.message.messageno, () => {
            this.$router.push({ name: "messageList" });
          });
        } else {
          deleteRecvMessage(this.message.messageno, () => {
            this.$router.push({ name: "messageList" });
          });
        }
      }
    },
  },
};
</script>

<style></style>
