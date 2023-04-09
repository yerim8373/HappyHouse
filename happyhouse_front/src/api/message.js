import { apiInstance } from "./index.js";

const api = apiInstance();

function listReceiveMessage(id, success, fail) {
  api.get(`/message/receive/${id}`).then(success).catch(fail);
}

function listSendMessage(id, success, fail) {
  api.get(`/message/send/${id}`).then(success).catch(fail);
}

function sendMessage(message, success, fail) {
  api.post(`/message`, JSON.stringify(message)).then(success).catch(fail);
}

function readMessage(messageno, id, success, fail) {
  api.get(`/message/read/${messageno}/${id}`).then(success).catch(fail);
}

function deleteSendMessage(messageno, success, fail) {
  api.get(`/message/delsend/${messageno}`).then(success).catch(fail);
}

function deleteRecvMessage(messageno, success, fail) {
  api.get(`/message/delrecv/${messageno}`).then(success).catch(fail);
}

export {
  listReceiveMessage,
  listSendMessage,
  sendMessage,
  readMessage,
  deleteRecvMessage,
  deleteSendMessage,
};
