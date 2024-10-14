import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import MenuView from '../views/MenuView.vue';
import CustomPizza from '../components/CustomPizza.vue';
import ToppingSelector from '../components/ToppingSelector.vue';
import PizzaCart from '../components/PizzaCart.vue';
import CustomerInfo from '../components/CustomerInfo.vue';
import CheckoutView from '../views/CheckoutView.vue';
import OrderInfo from '../components/OrderInfo.vue';
import CustomerInfoView from '../views/CustomerInfoView.vue';
import OrderConfirmation from '../components/OrderConfirmation.vue';
import EmployeeView from '../views/EmployeeView.vue';
import ToppingInventory from '../components/ToppingInventory.vue';
import PendingOrders from '../components/PendingOrders.vue';
import CreateSpecialtyPizza from '../components/CreateSpecialtyPizza.vue';
import ViewSpecialtyPizzas from '../components/ViewSpecialtyPizzas.vue';
import AboutUsView from '../views/AboutUsView.vue';
/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/menu",
    name: "menu",
    component: MenuView,
    meta: {
      requiresAuth: false
    }
  },

  {
    path: "/create-pizza",
    name: "create-pizza",
    component: CustomPizza,
    meta: {
      requiresAuth: false
    }
  },

  {
    path: "/cart",
    name: "pizza-cart",
    component: PizzaCart,
    meta: {
      requiresAuth: false
    }
  },

  {
    path: "/checkout",
    name: "checkout",
    component: CheckoutView,
    meta: { requiresAuth: false }
  },

  {
    path: "/customer-info",
    name: "customer-info",
    component: CustomerInfoView,
    meta: { requiresAuth: false }

  },

  {
    path: "/order-confirmation",
    name: "order-confirmation",
    component: OrderConfirmation,
    meta: { requiresAuth: false }
  },

  {
    path: "/employee-view",
    name: "employee-view",
    component: EmployeeView,
    meta: { requiresAuth: true }
  },

  {
    path: "/topping-inventory",
    name: "topping-inventory",
    component: ToppingInventory,
    meta: { requiresAuth: true }
  }, 

  {
    path: "/pending-orders",
    name: "pending-orders",
    component: PendingOrders,
    meta: { requiresAuth: true }
  },

  {
    path: "/create-specialty-pizza",
    name: "create-specialty-pizza",
    component: CreateSpecialtyPizza,
    meta: { requiresAuth: true }
  }, 

  {
    path: "/view-specialty-pizzas",
    name: "view-specialty-pizzas",
    component: ViewSpecialtyPizzas,
    meta: { requiresAuth: true }
  },
  {
    path: "/about-us",
    name: "about-us",
    component: AboutUsView,
    meta: { requiresAuth: false }
  }

];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return { name: "login" };
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
