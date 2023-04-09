import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(id, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${id}`).then(success).catch(fail);
}

function register(user, success, fail) {
  api.post(`/user/register`, JSON.stringify(user)).then(success).catch(fail);
}

function update(user, success, fail) {
  api.put(`/user/${user.id}`, JSON.stringify(user)).then(success).catch(fail);
}

function deleteUser(id, success, fail) {
  api.delete(`/user/${id}`).then(success).catch(fail);
}

export { login, findById, register, update, deleteUser };
