<template>
  <b-container class="bv-example-row mt-3">
    <img
      src="@/assets/commu.png"
      style="width: 200px; height: 70px; margin-top: 1px"
    />
    <hr />
    <br />
    <b-row class="mb-1">
      <b-col class="text-left">
        <div class="d-inline-flex form-row">
          <b-form-select
            class="col-4 mr-1"
            v-model="key"
            :options="options"
          ></b-form-select>
          <b-form-input class="col" v-model="word"></b-form-input>
        </div>
        <b-button
          class="ml-2"
          @click="searchArticle"
          style="background-color: #609ac0"
          >검색</b-button
        >
      </b-col>
      <b-col class="text-right">
        <b-button @click="moveWrite()" style="background-color: #609ac0"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <br />
    <b-row>
      <b-col v-if="articles.length">
        <b-table-simple hover responsive>
          <b-thead
            style="
              background-color: #609ac0;
              background: rgba(96, 154, 192, 0.7);
            "
          >
            <b-tr>
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>조회수</b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody
            style="
              background-color: #609ac0;
              background: rgba(171, 202, 222, 0.7);
            "
          >
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <board-list-item
              v-for="article in articles"
              :key="article.articleno"
              v-bind="article"
            />
          </tbody>
        </b-table-simple>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>
  </b-container>
</template>

<script>
import { listArticle, searchArticle } from "@/api/board";
import BoardListItem from "@/components/board/item/BoardListItem";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
  },
  data() {
    return {
      articles: [],
      key: "subject",
      word: null,
      options: [
        { value: "subject", text: "제목" },
        { value: "content", text: "내용" },
        { value: "id", text: "아이디" },
      ],
    };
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listArticle(
      param,
      (response) => {
        this.articles = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardRegister" });
    },
    searchArticle() {
      searchArticle(
        { key: this.key, word: this.word },
        (response) => {
          this.articles = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
