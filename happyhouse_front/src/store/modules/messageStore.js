const messageStore = {
  namespaced: true,
  state: {
    isOption: false, // false : 받은, true : 보낸
  },
  mutations: {
    SET_IS_OPTION: (state, isOption) => {
      state.isOption = isOption;
    },
  },
  actions: {
    setOption({ commit }, option) {
      commit("SET_IS_OPTION", option);
    },
  },
};

export default messageStore;
