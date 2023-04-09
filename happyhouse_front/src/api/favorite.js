import { apiInstance } from "./index.js";

const api = apiInstance();

function listFavorite(userid, success, fail) {
  api.get(`/favorite/${userid}`).then(success).catch(fail);
}

function addLikedItem(like, success, fail) {
  console.log(like);
  api.post(`/favorite`, JSON.stringify(like)).then(success).catch(fail);
}

function deleteLikedItem(like, success, fail) {
  api
    .delete(`/favorite/${like.userid}/${like.aptcode}`)
    .then(success)
    .catch(fail);
}

export { listFavorite, deleteLikedItem, addLikedItem };
