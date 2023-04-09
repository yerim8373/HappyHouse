<template>
  <div class="m-2">
    <div>
      <template>
        <b-col>
          <div id="aptimg">
            <img :src="apt.img" style="width: 250px; height: 150px" />
            <b-icon-heart-fill
              class="liked"
              @click="removeLikedItem(apt.aptcode)"
              type="heart"
              theme="filled"
              style="color: #f6685e"
              font-scale="1.5"
            ></b-icon-heart-fill></div
        ></b-col>

        <!-- <AIcon
          v-else
          @click="addLikedItem(index, apt.aptCode)"
          type="heart"
          style="color: #f6685e"
        /> -->
      </template>
    </div>
    <br />
    <div>
      <p style="font-weight: bold">{{ apt.aptname }}</p>
      <p>건축연도: {{ apt.buildyear }}</p>
      <p>거래가: {{ apt.dealAmount }}</p>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { deleteLikedItem } from "@/api/favorite";

const userStore = "userStore";

export default {
  name: "UserFavoriteItem",
  props: {
    apt: {
      type: Object,
      default: () => ({}),
    },
    index: {
      type: Number,
    },
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  data() {
    return {
      like: {
        userid: null,
        aptcode: 0,
      },
    };
  },
  created() {
    console.log(this.userInfo);
    console.log(this.apt);
    this.like.userid = this.userInfo.id;
  },
  methods: {
    removeLikedItem(aptcode) {
      this.like.aptcode = aptcode;
      console.log(this.like);
      deleteLikedItem(
        this.like,
        ({ data }) => {
          let msg = "관심목록 삭제 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "관심목록 삭제가 완료되었습니다.";
          }
          alert(msg);
          this.$router.go();
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style>
#aptimg {
  position: relative;
}
.liked {
  position: absolute;
  top: 10px;
  right: 10px;
}
</style>
