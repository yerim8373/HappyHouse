<template>
  <div class="item" @click="onItemClick">
    <b-row>
      <b-col md="5" class="item-left">
        <b-img class="item-img py-1" left :src="aptInfo.img" />
        <div>
          <b-icon
            icon="heart-fill"
            @click.stop="removeLikeItem(aptInfo.aptCode)"
            style="color: #f6685e"
            font-scale="1.5"
            class="like"
            v-if="liked"
          ></b-icon>
          <b-icon
            icon="heart"
            @click.stop="addLike(aptInfo.aptCode)"
            style="color: #f6685e"
            font-scale="1.5"
            class="like"
            v-else
          ></b-icon>
        </div>
      </b-col>
      <b-col md="7" class="px-2 pt-3 text-left">
        <h2 class="item-title">{{ aptInfo.dealAmount | priceConverter }}</h2>
        <p>
          아파트 : {{ aptInfo.aptName }}<br />전용 면적 :
          {{ aptInfo.area | areaConverter }} 제곱미터<br />
          거래 일시 : {{ aptInfo.dealYear }} 년 {{ aptInfo.dealMonth }} 월
          {{ aptInfo.dealDay }} 일
        </p>
      </b-col>
    </b-row>
    <hr class="item-split" />
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import { deleteLikedItem, addLikedItem } from "@/api/favorite";

const userStore = "userStore";
const aptStore = "aptStore";
const favoriteStore = "favoriteStore";

export default {
  name: "AptItem",
  props: {
    aptInfo: Object,
  },
  data() {
    return {
      liked: false,
      like: {
        userid: null,
        aptCode: 0,
      },
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    ...mapState(aptStore, ["aptDetail"]),
    ...mapState(favoriteStore, ["favoriteList"]),
  },
  created() {
    this.like.userid = this.userInfo.id;
    // console.log(this.aptInfo.aptCode);
    for (const favorite of this.favoriteList) {
      if (this.aptInfo.aptCode === favorite.aptcode) {
        this.liked = true;
      }
    }
  },
  filters: {
    priceConverter(price) {
      const _price = price.trim();

      if (_price.length <= 5) {
        const thousand = price.replace(",", "");

        return thousand;
      }

      const hundredMillion = _price.substring(0, _price.length - 5);
      const thousand = parseInt(
        _price.substring(_price.length - 5).replace(",", "")
      );
      const convertedPrice =
        thousand === 0
          ? `${hundredMillion}억`
          : `${hundredMillion}억 ${thousand}`;

      return convertedPrice;
    },
    areaConverter(area) {
      return parseFloat(area).toFixed(2);
    },
  },
  methods: {
    ...mapActions(aptStore, ["setAptDetail"]),
    onItemClick() {
      this.setAptDetail(this.aptInfo);
    },
    addLike(aptCode) {
      this.like.aptCode = aptCode;
      // console.log(this.like);
      addLikedItem(
        this.like,
        ({ data }) => {
          let msg = "관심목록 추가 처리시 문제가 발생했습니다.";
          // console.log(this.like.aptcode);
          if (data === "success") {
            msg = "관심목록 추가가 완료되었습니다.";
          }
          alert(msg);
          // this.$router.go();
          this.liked = true;
        },
        (error) => {
          // console.log(this.like.aptcode);
          console.log(error);
        }
      );
    },
    removeLikeItem(aptCode) {
      this.like.aptcode = aptCode;
      deleteLikedItem(
        this.like,
        ({ data }) => {
          let msg = "관심목록 삭제 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "관심목록 삭제가 완료되었습니다.";
          }
          alert(msg);
          // this.$router.go();
          this.liked = false;
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style scoped>
.item:hover {
  background-color: rgb(236, 236, 236);
}
.item {
  padding-top: 10px;
  padding-left: 10px;
  position: relative;
}
.item-img {
  width: 100%;
  height: 170px;
}
.item-left {
  padding-right: 0px;
  /*overflow: hidden;*/
}
.item-split {
  margin-bottom: 0px;
  margin-top: 10px;
}
.item-title {
  font-weight: bold;
}
.like {
  position: absolute;
  top: 10%;
  left: 80%;
}
</style>
