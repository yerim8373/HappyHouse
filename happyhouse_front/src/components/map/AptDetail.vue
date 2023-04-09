<template>
  <div class="left-side">
    <div>
      <b-button-close class="detail-close-button pr-2" @click="onCloseClick" />
      <b-img class="detail-img" :src="aptDetail.img"></b-img>
    </div>

    <div v-if="isWhenBuy">
      <AptDetailWhenBuy @child="setIsWhenBuy" />
    </div>
    <div v-else>
      <div class="mb-2 py-1 text-left">
        <p class="detail-price">
          {{ this.aptDetail.dealAmount | priceConverter }}
        </p>
        <p class="detail-apt-name">
          {{ this.aptDetail.aptName }}
        </p>
      </div>
      <div class="py-2 my-2">
        <table class="detail-table">
          <tr>
            <th>면적</th>
            <th>층</th>
            <th>건축연도</th>
          </tr>
          <tr>
            <td>{{ this.aptDetail.area || areaConverter }}m²</td>
            <td>{{ this.aptDetail.floor }}</td>
            <td>{{ this.aptDetail.buildYear }}</td>
          </tr>
        </table>
      </div>
      <div class="my-3">
        <img
          @click="onMartClick"
          class="detail-icon"
          src="@/assets/carts.png"
        />
        <img @click="onShopClick" class="detail-icon" src="@/assets/shop.png" />
        <img
          @click="onCafeClick"
          class="detail-icon"
          src="@/assets/coffee.png"
        />
        <img
          @click="onFoodClick"
          class="detail-icon"
          src="@/assets/fastfood.png"
        />
        <img
          @click="onChargingStationClick"
          class="detail-icon"
          src="@/assets/charger.png"
        />
      </div>
      <div class="mt-4">
        <b-button @click="onRodeviewClick" variant="success" class="btn-lg m-2"
          >로드뷰</b-button
        >
        <b-button @click="isWhenBuy = true" variant="primary" class="btn-lg m-2"
          >언제 살 수 있을까</b-button
        >
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import AptDetailWhenBuy from "@/components/map/AptDetailWhenBuy.vue";

const aptStore = "aptStore";
export default {
  data() {
    return {
      isWhenBuy: false,
    };
  },
  computed: {
    ...mapState(aptStore, ["aptDetail", "viewMap"]),
  },
  methods: {
    ...mapActions(aptStore, [
      "setAptDetail",
      "getStoreList",
      "clearStoreList",
      "setViewMap",
      "getChargingStationList",
      "clearChargingStationList",
    ]),
    onCloseClick() {
      this.setAptDetail(null);
      this.clearStoreList();
      this.setViewMap(true);
      this.clearChargingStationList();
    },
    onMartClick() {
      this.clearChargingStationList();
      this.getStoreList(["할인점", this.aptDetail.lat, this.aptDetail.lng]);
    },
    onShopClick() {
      this.clearChargingStationList();
      this.getStoreList(["편의점", this.aptDetail.lat, this.aptDetail.lng]);
    },
    onCafeClick() {
      this.clearChargingStationList();
      this.getStoreList(["카페", this.aptDetail.lat, this.aptDetail.lng]);
    },
    onFoodClick() {
      this.clearChargingStationList();
      this.getStoreList(["패스트푸드", this.aptDetail.lat, this.aptDetail.lng]);
    },
    onChargingStationClick() {
      this.clearStoreList();
      this.getChargingStationList([
        this.aptDetail.dongCode.substr(0, 2),
        this.aptDetail.lat,
        this.aptDetail.lng,
      ]);
    },
    onRodeviewClick() {
      this.setViewMap(!this.viewMap);
    },
    onWhenBuyClick() {
      this.isWhenBuy = true;
    },
    setIsWhenBuy() {
      this.isWhenBuy = false;
    },
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
  components: { AptDetailWhenBuy },
};
</script>

<style scoped>
.detail-close-button {
  font-size: 45pt;
  font-weight: 100;
}
.detail-img {
  width: 100%;
  height: 300px;
}
.left-side {
  overflow-x: hidden;
}
.detail-price {
  margin-top: 2rem;
  margin-left: 2rem;
  margin-bottom: 0.3rem;
  font-size: 37pt;
  font-weight: 700;
}
.detail-apt-name {
  margin-left: 2rem;
  font-size: x-large;
  font-weight: 500;
}
.detail-table {
  text-align: center;
  width: 100%;
}
.detail-table th {
  font-size: xx-large;
}
.detail-table td {
  color: rgb(0, 140, 255);
  font-size: x-large;
}
.detail-icon {
  width: 50px;
  height: auto;
  margin: 1rem;
  cursor: pointer;
}
</style>
