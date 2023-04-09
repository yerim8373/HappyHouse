<template>
  <div class="container">
    <div>
      <div class="row">
        <div>
          <div class="d-flex">
            <form action="initpage.do" method="post" id="rform">
              <div class="row w-100 justify-content-between">
                <b-form-select
                  v-model="sido"
                  :options="sido_list"
                  @change="sidoChange"
                  class="col-md-4 mr-1 mt-1"
                />
                <b-form-select
                  v-model="gugun"
                  :options="gugun_list"
                  @change="gugunChange"
                  class="col-md-4 mr-1 mt-1"
                />
              </div>
            </form>
          </div>
        </div>
        <div
          id="avg"
          style="width: 100%; height: 720px; margin: auto; overflow: auto"
          class="mb-3"
        >
          <template>
            <div>
              <b-table striped hover :items="deals" :fields="fields"> </b-table>
            </div>
          </template>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "@/api/index.js";

export default {
  created() {
    axios.get("/avg/sido").then((response) => {
      this.sido_list.push({ value: null, text: "시/도" });
      for (let d of response.data) {
        this.sido_list.push({ value: d.sidoCode, text: d.sidoName });
      }
    });
    this.gugun_list.push({ value: null, text: "구/군" });
  },
  data() {
    return {
      sido: null,
      gugun: null,
      deal: null,
      sido_list: [],
      gugun_list: [],
      deals: [],
      fields: ["순위", "동", "거래 평균 금액 (단위 : 백만)"],
    };
  },
  methods: {
    sidoChange() {
      this.gugun_list.splice(0, this.gugun_list.length);
      this.gugun_list.push({ value: null, text: "구/군" });
      this.gugun = null;
      this.gugunChange();
      if (this.sido === null) {
        return;
      }
      axios
        .get("/avg/gugun", { params: { sido: this.sido } })
        .then((response) => {
          // this.gugun_list.splice(0, this.gugun_list.length);
          // this.gugun_list.push({ value: null, text: "구/군" });
          console.log(response.data);
          for (let d of response.data) {
            this.gugun_list.push({ value: d.gugunCode, text: d.gugunName });
          }
        });
    },
    gugunChange() {
      this.deals.splice(0, this.deals.length);
      this.deal = null;
      if (this.gugun === null) {
        return;
      }
      axios
        .get("/avg/apt", { params: { gugun: this.gugun } })
        .then((response) => {
          //   console.log(response.data);
          //   this.deals = response.data;
          console.log(this.deals.length, this.deals);
          let i = 1;
          for (let d of response.data) {
            this.deals.push({
              순위: i++,
              동: d.dongName,
              "거래 평균 금액 (단위 : 백만)": d.dealAvg,
            });
          }
        });
    },
  },
};
</script>

<style>
.body {
  margin-top: 100px;
}

select {
  background-color: orange;
  color: white;
  border: 0;
  border-radius: 70px;
  padding: 7px;
}

select option {
  background-color: white;
  color: black;
  font-weight: 400;
}
</style>
