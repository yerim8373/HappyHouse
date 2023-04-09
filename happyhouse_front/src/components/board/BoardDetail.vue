<template>
  <b-container class="bv-example-row mt-3">
    <img
      src="@/assets/commu.png"
      style="width: 200px; height: 70px; margin-top: 1px"
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
          <div style="margin-top: 10px">
            <h3 style="display: inline; margin: 10px;t">
              {{ article.subject }}
            </h3>
            <div class="text-right">
              <span>작성자 {{ article.id }} &nbsp; | &nbsp;</span>
              <span>조회수 {{ article.hit }} &nbsp; | &nbsp;</span>
              <span>날짜 {{ article.regtime }}</span>
            </div>
          </div>
          <hr />
          <b-textarea plaintext :value="message" rows="15"></b-textarea>
        </b-col>
      </b-row>
      <b-row class="mb-1">
        <b-col class="text-right">
          <hr />
          <b-icon icon="pencil-square" style="color: #589ebb"></b-icon>
          <a size="sm" @click="moveModifyArticle" style="text-decoration: none"
            >글수정</a
          >
          &nbsp;&nbsp;&nbsp;
          <b-icon icon="trash" style="color: #589ebb"></b-icon>
          <a size="sm" @click="deleteArticle" style="text-decoration: none"
            >글삭제</a
          >
          <hr />
        </b-col>
      </b-row>
      <b-row>
        <b-col class="text-align">
          <b-button
            class="m-1"
            @click="listArticle"
            style="width: 200px; background-color: #609ac0"
            >목록</b-button
          >
        </b-col>
      </b-row>
    </div>
  </b-container>
</template>

<script>
// import moment from "moment";
import { getArticle, deleteArticle } from "@/api/board";

export default {
  name: "BoardDetail",
  data() {
    return {
      article: {},
    };
  },
  computed: {
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    getArticle(
      this.$route.params.articleno,
      (response) => {
        this.article = response.data;
        // this.article.hit = response.data.hit + 1;
      },
      (error) => {
        console.log("삭제 시 에러발생", error);
      }
    );
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "boardList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "boardModify",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        deleteArticle(this.article.articleno, () => {
          this.$router.push({ name: "boardList" });
        });
      }
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>
