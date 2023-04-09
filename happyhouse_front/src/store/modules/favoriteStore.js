import { listFavorite } from "@/api/favorite.js";

const favoriteStore = {
  namespaced: true,
  state: {
    favoriteList: [],
    // favoriteDetail: null,
  },
  getters: {},
  mutations: {
    SET_FAVORITE_LIST: (state, favoriteListData) => {
      for (let favoriteData of favoriteListData) {
        state.favoriteList.push(favoriteData);
      }
    },
    CLEAR_FAVORITE_LIST: (state) => {
      state.favoriteList.splice(0, state.favoriteList.length);
    },
    // SET_FAVORITE_DETAIL: (state, favoriteData) => {
    //   state.favoriteDetail = favoriteData;
    // },
  },
  actions: {
    // setFavoriteDetail: ({ commit }, data) => {
    //   commit("SET_FAVORITE_DETAIL", data);
    // },
    getFavoriteList: ({ commit }, userid) => {
      commit("CLEAR_FAVORITE_LIST");

      listFavorite(
        userid,
        ({ data }) => {
          //   console.log(data.response.body.items.item);
          commit("SET_FAVORITE_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default favoriteStore;
