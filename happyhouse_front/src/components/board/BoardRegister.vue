<template>
  <b-container class="bv-example-row mt-3">
    <img
      src="@/assets/commu.png"
      style="width: 200px; height: 70px; margin-top: 1px"
    />
    <hr />
    <br />
    <board-input-item type="register" />
  </b-container>
</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem.vue";

export default {
  name: "BoardWrite",
  components: {
    BoardInputItem,
  },
};
</script>

<style></style>
