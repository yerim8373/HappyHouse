import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";

import store from "@/store/index.js";

Vue.use(VueRouter);

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["userStore/checkUserInfo"];
  const getUserInfo = store._actions["userStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    next({ name: "login" });
    // router.push({ name: "signIn" });
  } else {
    // console.log("로그인 했다.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/map",
    name: "map",
    component: () => import("@/views/MapView.vue"),
    redirect: "/map/apt",
    children: [
      {
        path: "apt",
        name: "aptMap",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/map/AptMap.vue"),
      },
    ],
  },
  {
    path: "/deal",
    name: "deal",
    component: () => import("@/views/DealView.vue"),
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/BoardView.vue"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardList",
        component: () => import("@/components/board/BoardList.vue"),
      },
      {
        path: "write",
        name: "boardRegister",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardRegister.vue"),
      },
      {
        path: "detail/:articleno",
        name: "boardDetail",
        component: () => import("@/components/board/BoardDetail.vue"),
      },
      {
        path: "modify/:articleno",
        name: "boardModify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardModify.vue"),
      },
      // {
      //   path: "delete/:articleno",
      //   name: "boardDelete",
      //   beforeEnter: onlyAuthUser,
      //   component: () => import("@/components/board/BoardDelete.vue"),
      // },
    ],
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/UserView.vue"),
    children: [
      {
        path: "login",
        name: "login",
        component: () => import("@/components/user/UserLogin.vue"),
      },
      {
        path: "register",
        name: "register",
        component: () => import("@/components/user/UserRegister.vue"),
      },
      {
        path: "mypage",
        name: "mypage",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/user/UserMyPage.vue"),
      },
      {
        path: "favorite",
        name: "favorite",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/user/UserFavorite.vue"),
      },
      {
        path: "update",
        name: "update",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/user/UserModify.vue"),
      },
    ],
  },
  {
    path: "/message",
    name: "message",
    beforeEnter: onlyAuthUser,
    component: () => import("@/views/MessageView.vue"),
    redirect: "/message/list",
    children: [
      {
        path: "list",
        name: "messageList",
        component: () => import("@/components/message/MessageList.vue"),
      },
      {
        path: "send",
        name: "messageSend",
        component: () => import("@/components/message/MessageSend.vue"),
      },
      {
        path: "read/:messageno",
        name: "messageDetail",
        component: () => import("@/components/message/MessageDetail.vue"),
      },
      // {
      //   path: "modify/:articleno",
      //   name: "boardModify",
      //   beforeEnter: onlyAuthUser,
      //   component: () => import("@/components/board/BoardModify.vue"),
      // },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
