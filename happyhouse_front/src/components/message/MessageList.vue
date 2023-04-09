<template>
  <b-container class="bv-example-row mt-3">
    <img
      src="@/assets/msg.png"
      style="width: 120px; height: 70px; margin-top: 1px"
    />
    <hr />
    <br />
    <b-row class="mb-1">
      <b-col class="text-left">
        <div class="d-inline-flex form-row">
          <b-form-select
            v-model="selected"
            :options="options"
            @change="setMessage"
          ></b-form-select>
        </div>
        <!-- <b-button
          class="ml-2"
          style="background-color: #609ac0"
          @click="setMessage"
          >선택</b-button
        > -->
      </b-col>
      <b-col class="text-right">
        <b-button style="background-color: #609ac0" @click="moveSend()"
          >쪽지보내기</b-button
        >
      </b-col>
    </b-row>
    <br />
    <b-row>
      <b-col v-if="messages.length">
        <b-table-simple hover responsive>
          <b-thead
            style="
              background-color: #609ac0;
              background: rgba(96, 154, 192, 0.7);
            "
          >
            <b-tr>
              <b-th>제목</b-th>
              <b-th>수신자</b-th>
              <b-th>발신자</b-th>
              <b-th>보낸시간</b-th>
              <b-th>읽음</b-th>
            </b-tr>
          </b-thead>
          <tbody
            style="
              background-color: #609ac0;
              background: rgba(171, 202, 222, 0.7);
            "
          >
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <message-list-item
              v-for="message in messages"
              :key="message.messageno"
              v-bind="message"
            />
          </tbody>
        </b-table-simple>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>
  </b-container>
</template>

<script>
import { listReceiveMessage, listSendMessage } from "@/api/message";
import MessageListItem from "@/components/message/MessageListItem";
import { mapState, mapActions } from "vuex";

const userStore = "userStore";
const messageStore = "messageStore";

export default {
  name: "messageList",
  components: {
    MessageListItem,
  },
  data() {
    return {
      messages: [],
      selected: "receive",
      options: [
        { value: "receive", text: "받은 쪽지함" },
        { value: "send", text: "보낸 쪽지함" },
      ],
    };
  },
  created() {
    listReceiveMessage(
      this.userInfo.id,
      (response) => {
        this.messages = response.data;
        // console.log(this.messages);
      },
      (error) => {
        console.log(error);
      }
    );
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    ...mapState(messageStore, ["isOption"]),
  },
  methods: {
    ...mapActions(messageStore, ["setOption"]),
    moveSend() {
      this.$router.push({ name: "messageSend" });
    },
    setMessage() {
      if (this.selected === "receive") {
        this.setOption(false);
        listReceiveMessage(
          this.userInfo.id,
          (response) => {
            this.messages = response.data;
          },
          (error) => {
            console.log(error);
          }
        );
      } else {
        this.setOption(true);
        listSendMessage(
          this.userInfo.id,
          (response) => {
            this.messages = response.data;
            console.log(this.messages);
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
  },
};
</script>

<style></style>
