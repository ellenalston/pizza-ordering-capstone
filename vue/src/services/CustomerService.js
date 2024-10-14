import axios from 'axios';

export default {

    // Create a new customer
    createCustomer(newCustomer){
        return axios.post("/create-customer", newCustomer);
    },

    //Create a new order
    createOrder(order){
        return axios.post("/create-order", order);
    }, 

    getOrders(){
        return axios.get("/orders");
    }, 

    completeOrder(orderId){ 
        return axios.put(`/order/${orderId}/complete`);
    },

    cancelOrder(orderId){ 
        return axios.put(`/order/${orderId}/cancel`);
    }, 

    getCustomerById(customerId){
        return axios.get(`/customer/${customerId}`);
    }


}