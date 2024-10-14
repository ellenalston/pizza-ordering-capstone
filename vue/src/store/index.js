import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      cart: [],
      customerId: '', 
      totalPrice: 0,
      orderData: {}
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      
      ADD_TO_CART(state, pizza) {
        console.log(pizza);
        state.cart.push(pizza); 
      },
      REMOVE_FROM_CART(state, pizzaIndex) {
        state.cart.splice(pizzaIndex, 1); 
      },
      CLEAR_CART(state) {
        state.cart = [];
      }, 
      SET_CUSTOMER_ID(state, customerId) {
        state.customerId = customerId;
      },
      SET_TOTAL_PRICE(state, totalPrice) {
        state.totalPrice = totalPrice;
      }, 
      SET_ORDER_DATA(state, orderData) {
        state.orderData = orderData;
      }
    },
  });
  return store;
}
