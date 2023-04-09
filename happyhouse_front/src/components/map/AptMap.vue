<template>
  <b-overlay :show="loading">
    <b-container fluid>
      <b-row>
        <b-col md="3" class="left-element">
          <template v-if="aptDetail == null">
            <template v-if="aptList.length !== 0">
              <AptItem
                v-for="(aptInfo, index) in aptList"
                :aptInfo="aptInfo"
                :key="index"
              />
            </template>
            <template v-else>
              <div style="margin-right: 50px; margin-left: 50px">
                <b-img
                  center
                  :src="require('../../assets/noresult.png')"
                  fluid
                  style="margin: 300px"
                ></b-img>
              </div>
            </template>
          </template>
          <template v-else>
            <AptDetail />
          </template>
        </b-col>
        <b-col md="9" class="right-element">
          <div class="p-2">
            <div class="d-flex">
              <b-form-datepicker
                v-model="startDate"
                @hidden="startDateChange"
                min="2015-01-01"
                max="2022-04-30"
              ></b-form-datepicker>
              <b-form-datepicker
                v-model="endDate"
                @hidden="endDateChange"
                min="2015-01-01"
                max="2022-04-30"
              ></b-form-datepicker>
              <b-form-select
                class="mx-1"
                v-model="sidoCode"
                :options="sidoList"
                @change="sidoChange"
              />
              <b-form-select
                class="mx-1"
                v-model="gugunCode"
                :options="gugunList"
                @change="gugunChange"
              />
              <b-form-select
                class="mx-1"
                v-model="dongCode"
                :options="dongList"
                @change="dongChange"
              />
            </div>
          </div>
          <KakaoMap />
        </b-col>
      </b-row>
    </b-container>
  </b-overlay>
</template>

<script>
import KakaoMap from "@/components/map/KakaoMap.vue";
import AptItem from "@/components/map/AptItem.vue";
import AptDetail from "@/components/map/AptDetail.vue";
import { mapState, mapActions } from "vuex";

const userStore = "userStore";
const aptStore = "aptStore";
const favoriteStore = "favoriteStore";

export default {
  components: { KakaoMap, AptItem, AptDetail },
  created() {
    this.userid = this.userInfo.id;
    this.getSido();
    this.initAptPage();
    this.getFavoriteList(this.userid);
  },
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      startDate: "2021-01-01",
      beforeStartDate: null,
      endDate: "2021-06-30",
      beforeEndDate: null,
      userid: null,
    };
  },
  watch: {
    startDate: function (after, before) {
      this.beforeStartDate = before;
    },
    endDate: function (after, before) {
      this.beforeEndDate = before;
    },
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    ...mapState(aptStore, [
      "sidoList",
      "gugunList",
      "dongList",
      "aptList",
      "aptDetail",
      "loading",
    ]),
  },
  methods: {
    ...mapActions(aptStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getAptList",
      "initAptPage",
    ]),
    ...mapActions(favoriteStore, ["getFavoriteList"]),
    sidoChange() {
      this.gugunCode = null;
      this.dongCode = null;
      if (this.sidoCode !== null) this.getGugun(this.sidoCode);
    },
    gugunChange() {
      this.dongCode = null;
      if (this.gugunCode !== null) this.getDong(this.gugunCode);
    },
    dongChange() {
      this.getAptList([this.dongCode, this.startDate, this.endDate]);
    },
    startDateChange() {
      let s = new Date(this.startDate);
      let e = new Date(this.endDate);
      if (s >= e) {
        console.log("s " + s);
        console.log("e " + e);
        alert("시작일이 종료일보다 늦을 수 없습니다");
        this.startDate = this.beforeStartDate;
        return;
      }

      if (this.dongCode != null)
        this.getAptList([this.dongCode, this.startDate, this.endDate]);
    },
    endDateChange() {
      let s = new Date(this.startDate);
      let e = new Date(this.endDate);
      console.log(s, e);
      if (s >= e) {
        alert("종료일이 시작일보다 빠를 수 없습니다");
        this.endDate = this.beforeEndDate;
        return;
      }

      if (this.dongCode != null)
        this.getAptList([this.dongCode, this.startDate, this.endDate]);
    },
  },
};
</script>

<style scoped>
.left-element {
  padding-right: 0px;
  padding-left: 0px;
  height: 91vh;
  overflow-y: auto;
  overflow-x: hidden;
  background-color: rgba(255, 255, 255, 0.7);
}

.right-element {
  padding-right: 0px;
  padding-left: 0px;
  background-color: rgba(255, 255, 255, 0.7);
}
.empty-result {
  position: absolute;
  top: 47%;
  left: 5%;
}
</style>
