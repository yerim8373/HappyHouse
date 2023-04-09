import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

import userStore from "@/store/modules/userStore.js";
import aptStore from "@/store/modules/aptStore";
import favoriteStore from "./modules/favoriteStore";
import messageStore from "./modules/messageStore";

const store = new Vuex.Store({
  modules: {
    userStore,
    aptStore,
    favoriteStore,
    messageStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});

export default store;

// export default new Vuex.Store({
//   plugins: [
//     createPersistedState({
//       storage: window.sessionStorage, // store를 session storage 에 유지
//     }),
//   ],
//   state: {},
//   getters: {},
//   mutations: {},
//   actions: {},
//   modules: {},
// });
