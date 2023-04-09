<template>
  <div class="pt-3">
    <b-row>
      <b-col cols="6" class="left-element">
        {{ this.aptDetail.aptName }}
      </b-col>
      <b-col cols="6" class="right-element">
        {{ this.aptDetail.dealAmount | priceConverter }} 만원
      </b-col>
    </b-row>
    <b-row class="mt-3">
      <b-col cols="6" class="left-element"> 현재 금리 </b-col>
      <b-col cols="6" class="right-element"> {{ interestRate }}% </b-col>
    </b-row>
    <hr class="m-1" />
    <div v-if="!nextPage">
      <b-container>
        <div class="empty-element" v-if="savings === null || deposit === null">
          <b-img center :src="require('../../assets/input.png')" fluid></b-img>
        </div>
        <div class="empty-element" v-else>정보 입력 완료!</div>
        <div class="m-2">
          <div class="mt-3 left-element">월 저축 금액 (단위:만원)</div>
          <b-form-input
            v-model="savings"
            placeholder="월 저축 금액을 입력해 주세요"
            class="money-input"
          ></b-form-input>
        </div>
        <div class="m-2 text-center">
          <div class="mt-3 left-element">은행 잔고 (단위:만원)</div>
          <b-form-input
            v-model="deposit"
            placeholder="은행 잔고를 입력해 주세요"
            class="money-input"
          ></b-form-input>
        </div>
        <b-button
          class="result-button"
          disabled
          v-if="savings === null || deposit === null"
          >정보를 입력해 주세요!</b-button
        >
        <b-button v-else class="result-button" @click="calculate"
          >결과 확인하기!</b-button
        >
      </b-container>
    </div>
    <div v-else>
      <b-container>
        <div class="empty-element">이 집을 구매하려면?</div>
        <div class="m-2">
          <div class="mt-3 left-element">필요한 금액은</div>
          <div class="mt-1 right-element">
            {{ this.aptDetail.dealAmount.replace(",", "") - this.deposit }}만원
          </div>
        </div>
        <div class="m-2 text-center">
          <div class="mt-3 left-element">
            월 {{ this.savings }}만원 저축으로 걸리는 기간
          </div>
          <div class="mt-1 right-element">{{ this.year }}년</div>
        </div>
        <div class="mt-4">
          <b-button class="m-1" @click="turnBack">되돌아 가기!</b-button>
          <b-button class="m-1" @click="exit"> 나가기! </b-button>
        </div>
      </b-container>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const userStore = "userStore";
const aptStore = "aptStore";

export default {
  computed: {
    ...mapState(userStore, ["userInfo"]),
    ...mapState(aptStore, ["aptDetail", "financeInfo"]),
  },
  data() {
    return {
      savings: null,
      deposit: null,
      nextPage: false,
      remain: null,
      interestRate: 2.5,
      year: 0,
    };
  },
  methods: {
    calculate() {
      const totalMoney = this.aptDetail.dealAmount.replace(",", "");
      let sum = parseInt(this.deposit);
      const ir = this.interestRate / 100 + 1;

      this.year = 0;
      while (sum < totalMoney) {
        sum = parseInt(this.savings) + sum * ir;
        console.log(sum);
        this.year++;
      }
      this.nextPage = true;
      console.log("sum " + sum + " totalMoney : " + totalMoney);
    },
    turnBack() {
      this.nextPage = false;
    },
    exit() {
      this.$emit("child");
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
  },
};
</script>

<style scoped>
.left-element {
  font-weight: 600;
  font-size: x-large;
  background-color: lightgray;
}
.right-element {
  font-weight: 600;
  font-size: x-large;
}
.empty-element {
  color: red;
  font-weight: 500;
  font-size: large;
}
.result-button {
  margin: 2rem;
  width: 60%;
}
.money-input {
  margin-top: 0.5rem;
}
</style>
