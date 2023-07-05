import axios from 'axios'
import { getToken } from './AuthService';



const BASE_URL = 'http://localhost:8080/api/v1/todos';


  // Add a request interceptor
  axios.interceptors.request.use(function (config) {
      // Do something before request is sent
      config.headers['Authorization'] = getToken();

      return config;
    }, function (error) {
      // Do something with request error
       return Promise.reject(error);
    });



export const listTodos = () => axios.get(BASE_URL);

export const createTodo = (todo) => axios.post(BASE_URL, todo);

export const getTodo = (id) => axios.get(`${BASE_URL}/${id}`);

export const editTodo = (id, todo) => axios.put(`${BASE_URL}/${id}`, todo);

export const deleteTodo = (id) => axios.delete(`${BASE_URL}/${id}`);

export const completeTodo = (id) => axios.patch(`${BASE_URL}/${id}/complete`);

export const inCompleteTodo = (id) => axios.patch(`${BASE_URL}/${id}/in-complete`);


