import {
  getSidoCodes,
  getGugunCodes,
  getDongCodes,
  getAptList,
  getStoreList,
  getChargingStationList,
} from "@/api/apt.js";

const aptStore = {
  namespaced: true,
  state: {
    sidoList: [{ value: null, text: "시/도" }],
    gugunList: [{ value: null, text: "구/군" }],
    dongList: [{ value: null, text: "동" }],
    aptList: [],
    aptDetail: null,
    storeList: [],
    chargingStationList: [],
    viewMap: true,
    financeInfo: {
      salary: 0,
      deposit: 0,
      interestRate: 2.5,
    },
    loading: false,
  },

  getters: {},

  mutations: {
    SET_LOADING: (state, data) => {
      state.loading = data;
    },
    SET_APT_DETAIL: (state, aptData) => {
      state.aptDetail = aptData;
    },
    SET_SIDO_LIST: (state, sidoListData) => {
      sidoListData.forEach((sidoData) => {
        state.sidoList.push({
          value: sidoData.sidoCode,
          text: sidoData.sidoName,
        });
      });
    },
    SET_GUGUN_LIST: (state, gugunListData) => {
      gugunListData.forEach((gugunData) => {
        state.gugunList.push({
          value: gugunData.gugunCode,
          text: gugunData.gugunName,
        });
      });
    },
    SET_DONG_LIST: (state, dongListData) => {
      dongListData.forEach((dongData) => {
        state.dongList.push({
          value: dongData.dongCode,
          text: dongData.dong,
        });
      });
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidoList.splice(0, state.sidoList.length);
      state.sidoList.push({ value: null, text: "시/도" });
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.gugunList.splice(0, state.gugunList.length);
      state.gugunList.push({ value: null, text: "구/군" });
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongList.splice(0, state.dongList.length);
      state.dongList.push({ value: null, text: "동" });
    },
    SET_HOUSE_LIST: (state, aptListData) => {
      for (let aptData of aptListData) {
        state.aptList.push(aptData);
      }
      //state.houses = aptListData;
    },
    CLEAR_APT_LIST: (state) => {
      state.aptList.splice(0, state.aptList.length);
    },
    SET_STORE_LIST: (state, storeListData) => {
      for (let storeData of storeListData) {
        state.storeList.push(storeData);
      }
    },
    CLEAR_STORE_LIST: (state) => {
      state.storeList.splice(0, state.storeList.length);
    },
    SET_CHARGING_STATION_LIST: (state, chargingStationListData) => {
      for (let chargingStationData of chargingStationListData) {
        state.chargingStationList.push(chargingStationData);
      }
    },
    CLEAR_CHARGING_STATION_LIST: (state) => {
      state.chargingStationList.splice(0, state.chargingStationList.length);
    },
    SET_VIEW_MAP: (state, data) => {
      state.viewMap = data;
    },
    SET_FINANCE_INFO: (state, data) => {
      state.financeInfo = data;
    },
  },

  actions: {
    initAptPage: ({ commit }) => {
      commit("CLEAR_APT_LIST");
      commit("SET_APT_DETAIL", null);
    },
    setAptDetail: ({ commit }, data) => {
      commit("SET_APT_DETAIL", data);
    },
    getSido: ({ commit }) => {
      getSidoCodes(
        ({ data }) => {
          // console.log(data);
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      commit("CLEAR_GUGUN_LIST");
      commit("CLEAR_DONG_LIST");
      const params = {
        sido: sidoCode,
      };
      getGugunCodes(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDong: ({ commit }, gugunCode) => {
      commit("CLEAR_DONG_LIST");
      const params = {
        gugun: gugunCode,
      };
      getDongCodes(
        params,
        ({ data }) => {
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getAptList: ({ commit }, [dongCode, startDate, endDate]) => {
      commit("SET_LOADING", true);
      commit("CLEAR_APT_LIST");
      const params = {
        dong: dongCode,
        startDate: startDate,
        endDate: endDate,
      };
      getAptList(
        params,
        ({ data }) => {
          //   console.log(response.data.response.body.items.item);
          commit("SET_HOUSE_LIST", data);
          commit("SET_LOADING", false);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
    getStoreList: ({ commit }, [type, lat, lng]) => {
      commit("CLEAR_STORE_LIST");
      const params = {
        type: type,
        lat: lat,
        lng: lng,
      };
      getStoreList(
        params,
        ({ data }) => {
          commit("SET_STORE_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    clearStoreList: ({ commit }) => {
      commit("CLEAR_STORE_LIST");
    },
    getChargingStationList: ({ commit }, [sidoCode, lat, lng]) => {
      commit("CLEAR_CHARGING_STATION_LIST");
      const params = {
        sidoCode,
        lat,
        lng,
      };
      getChargingStationList(
        params,
        ({ data }) => {
          commit("SET_CHARGING_STATION_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    clearChargingStationList: ({ commit }) => {
      commit("CLEAR_CHARGING_STATION_LIST");
    },
    setViewMap: ({ commit }, data) => {
      commit("SET_VIEW_MAP", data);
    },

    setFinanceInfo: ({ commit }, data) => {
      commit("SET_FINANCE_INFO", data);
    },
  },
};

export default aptStore;
