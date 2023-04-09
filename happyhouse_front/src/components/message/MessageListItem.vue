<template>
  <b-tr>
    <b-th class="text-left">
      <router-link
        :to="{ name: 'messageDetail', params: { messageno: messageno } }"
        style="text-decoration: none; color: black"
        >{{ title }}</router-link
      >
    </b-th>
    <b-td>{{ receiver }}</b-td>
    <b-td>{{ sender }}</b-td>
    <b-td>{{ regtime | dateFormat }}</b-td>
    <b-td>
      <b-icon
        icon="envelope"
        style="color: #589ebb"
        v-if="readstate === 'N'"
      ></b-icon>
      <b-icon icon="envelope-open" style="color: #589ebb" v-else></b-icon>
    </b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "messageListItem",
  props: {
    messageno: Number,
    sender: String,
    receiver: String,
    title: String,
    content: String,
    regtime: String,
    readstate: String,
  },
  created() {
    // console.log(this.readstate);
    console.log(this.messageno);
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD HH:mm:ss");
    },
  },
  methods: {
    // readCheck() {
    //   if (this.readState === "N") {
    //     console.log("?????");
    //     return true;
    //   } else {
    //     return false;
    //   }
    // },
  },
};
</script>

<style></style>
