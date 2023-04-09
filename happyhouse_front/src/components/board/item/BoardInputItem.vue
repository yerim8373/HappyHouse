<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          id="id-group"
          label="작성자"
          label-for="id"
          label-cols-sm="4"
          label-cols-lg="3"
          content-cols-sm
          content-cols-lg="7"
          description="작성자를 입력하세요."
        >
          <b-form-input
            id="id"
            :disabled="isUserid"
            v-model="article.id"
            type="text"
            required
            placeholder="작성자 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="subject-group"
          label="제목"
          label-for="subject"
          label-cols-sm="4"
          label-cols-lg="3"
          content-cols-sm
          content-cols-lg="7"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="subject"
            v-model="article.subject"
            type="text"
            required
            placeholder="제목 입력..."
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
            v-model="article.content"
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
            v-if="this.type === 'register'"
            >글 작성</b-button
          >
          <b-button
            type="submit"
            class="m-1"
            style="width: 200px; background-color: #609ac0"
            v-else
            >글 수정</b-button
          >
          <b-button
            type="reset"
            class="m-1"
            style="width: 200px; background-color: #609ac0"
            >초기화</b-button
          >
        </div>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { writeArticle, getArticle, modifyArticle } from "@/api/board";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "BoardInputItem",
  data() {
    return {
      article: {
        articleno: 0,
        id: "",
        subject: "",
        content: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  created() {
    if (this.type === "modify") {
      getArticle(
        this.$route.params.articleno,
        ({ data }) => {
          // this.article.articleno = data.article.articleno;
          // this.article.userid = data.article.userid;
          // this.article.subject = data.article.subject;
          // this.article.content = data.article.content;
          console.log(data);
          this.article = data;
        },
        (error) => {
          console.log(error);
        }
      );
      this.isUserid = true;
    } else {
      this.article.id = this.userInfo.id;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.article.id &&
        ((msg = "작성자 입력해주세요"), (err = false), this.$refs.id.focus());
      err &&
        !this.article.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.article.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else
        this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.articleno = 0;
      this.article.subject = "";
      this.article.content = "";
      this.$router.push({ name: "boardList" });
    },
    registArticle() {
      writeArticle(
        {
          id: this.article.id,
          subject: this.article.subject,
          content: this.article.content,
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
    modifyArticle() {
      modifyArticle(
        {
          articleno: this.article.articleno,
          id: this.article.id,
          subject: this.article.subject,
          content: this.article.content,
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "boardList" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "boardList" });
    },
  },
};
</script>

<style></style>
