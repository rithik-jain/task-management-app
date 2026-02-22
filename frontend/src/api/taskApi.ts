import axios from "axios";
import { Task } from "../types/Task";

const API_URL = "http://localhost:8080/api/tasks";

export const getTasks = (page = 0, isCompleted?: boolean) => {
  let url = `${API_URL}?page=${page}&size=10`;
  if (isCompleted !== undefined) {
    url += `&isCompleted=${isCompleted}`;
  }
  return axios.get(url);
};

export const getTaskById = (id: number) =>
  axios.get(`${API_URL}/${id}`);

export const createTask = (task: Task) =>
  axios.post(API_URL, task);

export const updateTask = (id: number, task: Task) =>
  axios.put(`${API_URL}/${id}`, task);

export const deleteTask = (id: number) =>
  axios.delete(`${API_URL}/${id}`);

export const toggleTask = (id: number) =>
  axios.patch(`${API_URL}/${id}/toggle`);