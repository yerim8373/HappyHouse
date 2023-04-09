import { apiInstance } from "./index.js";

const api = apiInstance();

function getSidoCodes(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function getGugunCodes(param, success, fail) {
  api.get(`/map/gugun`, { params: param }).then(success).catch(fail);
}

function getDongCodes(param, success, fail) {
  api.get(`/map/dong`, { params: param }).then(success).catch(fail);
}

function getAptList(param, success, fail) {
  api.get(`/map/apt`, { params: param }).then(success).catch(fail);
}

function getStoreList(param, success, fail) {
  api.get(`/store/type`, { params: param }).then(success).catch(fail);
}

function getChargingStationList(param, success, fail) {
  api.get(`/chargingStation`, { params: param }).then(success).catch(fail);
}
// function listArticle(param, success, fail) {
//   api.get(`/board`, { params: param }).then(success).catch(fail);
// }

// function writeArticle(article, success, fail) {
//   api.post(`/board`, JSON.stringify(article)).then(success).catch(fail);
// }

// function getArticle(articleno, success, fail) {
//   api.get(`/board/${articleno}`).then(success).catch(fail);
// }

// function modifyArticle(article, success, fail) {
//   api
//     .put(`/board/${article.articleno}`, JSON.stringify(article))
//     .then(success)
//     .catch(fail);
// }

// function deleteArticle(articleno, success, fail) {
//   api.delete(`/board/${articleno}`).then(success).catch(fail);
// }

export {
  getSidoCodes,
  getGugunCodes,
  getDongCodes,
  getAptList,
  getStoreList,
  getChargingStationList,
};
