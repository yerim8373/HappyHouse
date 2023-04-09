<template>
  <b-container class="bv-example-row mt-3">
    <b-overlay :show="loading">
      <b-row>
        <b-card
          class="text-center mt-4"
          style="
            border-style: double;
            border-color: #609ac0;
            background: #f3f3f6;
            background: rgba(255, 255, 255, 0.5);
          "
        >
          <img
            src="@/assets/like.png"
            style="width: 200px; height: 70px; margin-top: 1px"
          />
          <hr />
          <b-container class="bv-example-row text-center">
            <b-row style="justify-content: center">
              <UserFavoriteItem
                v-for="(item, index) in favorites"
                :key="index"
                :apt="item"
                :index="index"
              />
            </b-row>
          </b-container>
        </b-card>
      </b-row>
    </b-overlay>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import { listFavorite } from "@/api/favorite";
import UserFavoriteItem from "@/components/user/UserFavoriteItem.vue";

const userStore = "userStore";

export default {
  name: "UserFavorite",
  components: {
    UserFavoriteItem,
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  data() {
    return {
      favorites: [],
      loding: false,
    };
  },
  created() {
    let userid = this.userInfo.id;
    this.loading = true;
    listFavorite(
      userid,
      (response) => {
        this.favorites = response.data;
        console.log(this.favorites);
        this.loading = false;
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style></style>
SET_LOADING: (state, data) => { state.loading = data; },
