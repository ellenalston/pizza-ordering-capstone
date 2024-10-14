package com.techelevator.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.techelevator.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcPizzaDao implements PizzaDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPizzaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Pizza> getPizzasByOrderId(int orderId){
        List <Pizza> pizzas = new ArrayList<>();

        String sql = "SELECT * FROM pizza p JOIN order_item oi ON p.pizza_id = oi.pizza_id WHERE oi.order_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, orderId);

        while(results.next()){
            Pizza pizza = mapRowtoPizza(results);
            pizzas.add(getPizzaById(pizza.getId()));
        }

        return pizzas;
    }

    // Update Methods
    @Override
    public void toggleToppingAvailability(int toppingId){

        String queryUpdate = "UPDATE topping SET is_available = NOT is_available WHERE topping_id = ?";
        jdbcTemplate.update(queryUpdate, toppingId);


    }

    @Override
    public void togglePizzaAvailability(int pizzaId){
        String queryUpdate = "UPDATE pizza SET is_available = NOT is_available WHERE pizza_id = ?";
        jdbcTemplate.update(queryUpdate,pizzaId);

    }

    public void completeOrder(int orderId){
        String queryUpdate = "UPDATE orders SET status = 'Completed' WHERE order_id = ?;";
        jdbcTemplate.update(queryUpdate, orderId);
    }

    public void cancelOrder(int orderId){
        String queryUpdate = "UPDATE orders SET status = 'Cancelled' WHERE order_id = ?;";
        jdbcTemplate.update(queryUpdate, orderId);
    }


    //Lists

    @Override
    public List<Order> getOrders(){
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Order order = mapRowtoOrder(results);
            orders.add(order);
        }
        return orders;
    }
    @Override
    public List<Crust> getCrusts() {
        List<Crust> crusts = new ArrayList<>();
        String sql = "SELECT * FROM crust;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Crust crust = mapRowtoCrust(results);
            crusts.add(crust);
        }
        return crusts;
    }

    @Override
    public List<Size> getSizes() {
        List<Size> sizes = new ArrayList<>();
        String sql = "SELECT * FROM size;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Size size = mapRowtoSize(results);
            sizes.add(size);
        }
        return sizes;
    }

    @Override
    public List<Sauce> getSauces() {
        List<Sauce> sauces = new ArrayList<>();
        String sql = "SELECT * FROM sauce;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Sauce sauce = mapRowtoSauce(results);
            sauces.add(sauce);
        }
        return sauces;
    }

    @Override
    public List<Topping> getRegularToppings() {
        List<Topping> toppings = new ArrayList<>();
        String sql = "SELECT * FROM topping WHERE topping_type = 'Regular';";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Topping topping = mapRowtoTopping(results);
            toppings.add(topping);
        }
        return toppings;
    }

    @Override
    public List<Topping> getPremiumToppings() {
        List<Topping> toppings = new ArrayList<>();
        String sql = "SELECT * FROM topping WHERE topping_type = 'Premium';";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Topping topping = mapRowtoTopping(results);
            toppings.add(topping);
        }
        return toppings;
    }


    @Override
    public List<Pizza> getSpecialtyPizzas() {
        List<Pizza> specialtyPizzas = new ArrayList<>();
        String sql = "SELECT pizza_id FROM pizza WHERE discount_id = 2;";
        SqlRowSet ids = jdbcTemplate.queryForRowSet(sql);
        while (ids.next()) {
            int id = ids.getInt(1);
            Pizza pizza = getPizzaById(id);
            specialtyPizzas.add(pizza);
        }
        return specialtyPizzas;
    }

    @Override
    public Pizza getPizzaById(int pizzaId) {
        Pizza pizza = null;

        String sql = "SELECT * FROM pizza WHERE pizza_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, pizzaId);

        if (results.next()) {
            pizza = mapRowtoPizza(results);
        }

        pizza.setToppings(getToppingsByPizzaId(pizza.getId()));
        pizza.setCrust(getCrustByCrustId(pizza.getCrustId()));
        pizza.setSauce(getSauceBySauceId(pizza.getSauceId()));
        pizza.setSize(getSizeBySizeId(pizza.getSizeId()));

        return pizza;
    }

    @Override
    public List<Topping> getToppingsByPizzaId(int pizzaId) {
        List<Topping> toppings = new ArrayList<>();
        String sql = "SELECT * FROM topping JOIN pizza_topping ON topping.topping_id = pizza_topping.topping_id WHERE pizza_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, pizzaId);
        while (results.next()) {
            Topping topping = mapRowtoTopping(results);
            toppings.add(topping);
        }
        return toppings;
    }

    @Override
    public Pizza createPizza(Pizza newPizza) {

        String sql = "INSERT INTO pizza (specialty_name, size_id, crust_id, sauce_id, is_available, discount_id, special_desc) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING pizza_id;";

        int newPizzaId = jdbcTemplate.queryForObject(sql, Integer.class,
                newPizza.getSpecialtyName(),
                newPizza.getSize().getId(),
                newPizza.getCrust().getId(),
                newPizza.getSauce().getId(),
                newPizza.isAvailable(),
                newPizza.getDiscountId(),
                newPizza.getSpecialDesc());

        addToppings(newPizzaId, newPizza.getToppings());

        return getPizzaById(newPizzaId);

    }

    public void addToppings(int pizzaId, List<Topping> toppings) {
        String toppingSql = "INSERT INTO pizza_topping (pizza_id, topping_id) VALUES (?, ?);";
        for (Topping topping : toppings) {
            jdbcTemplate.update(toppingSql, pizzaId, topping.getId());
        }
    }

    public BigDecimal calculatePrice(int pizzaId) {
        String sql = "SELECT (s.size_price + c.crust_price + sc.sauce_price + COALESCE(SUM(t.topping_price), 0)) AS total_price, " +
                "COALESCE(d.percentage, 0) AS discount_percentage " +
                "FROM pizza p " +
                "JOIN size s ON p.size_id = s.size_id " +
                "JOIN crust c ON p.crust_id = c.crust_id " +
                "JOIN sauce sc ON p.sauce_id = sc.sauce_id " +
                "LEFT JOIN pizza_topping pt ON p.pizza_id = pt.pizza_id " +
                "LEFT JOIN topping t ON pt.topping_id = t.topping_id " +
                "LEFT JOIN discount d ON p.discount_id = d.discount_id " +
                "WHERE p.pizza_id = ? " +
                "GROUP BY s.size_price, c.crust_price, sc.sauce_price, d.percentage;";

        Map<String, Object> results = jdbcTemplate.queryForMap(sql, pizzaId);

        BigDecimal totalPrice = BigDecimal.valueOf(((Number) results.get("total_price")).doubleValue());
        BigDecimal discountPercentage = BigDecimal.valueOf(((Number) results.get("discount_percentage")).doubleValue());

        // Apply discount if applicable and round to 2 decimal places
        return totalPrice.subtract(
                totalPrice.multiply(discountPercentage).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP)
        ).setScale(2, RoundingMode.HALF_UP);
    }

// Mapping Methods

    private Pizza mapRowtoPizza(SqlRowSet rs) {
        Pizza pizza = new Pizza();
        pizza.setId(rs.getInt("pizza_id"));
        pizza.setSpecialtyName(rs.getString("specialty_name"));
        pizza.setSizeId(rs.getInt("size_id"));
        pizza.setCrustId(rs.getInt("crust_id"));
        pizza.setSauceId(rs.getInt("sauce_id"));
        pizza.setAvailable(rs.getBoolean("is_available"));
        pizza.setDiscountId(rs.getInt("discount_id"));
        pizza.setSpecialDesc(rs.getString("special_desc"));
        return pizza;
    }

    private Topping mapRowtoTopping(SqlRowSet rs) {
        Topping topping = new Topping();

        topping.setId(rs.getInt("topping_id"));
        topping.setName(rs.getString("topping_name"));
        topping.setType(rs.getString("topping_type"));
        topping.setPrice(rs.getBigDecimal("topping_price"));
        topping.setAvailable(rs.getBoolean("is_available"));

        return topping;
    }

    private Crust mapRowtoCrust(SqlRowSet rs) {
        Crust crust = new Crust();

        crust.setId(rs.getInt("crust_id"));
        crust.setName(rs.getString("crust_name"));
        crust.setPrice(rs.getBigDecimal("crust_price"));

        return crust;
    }

    private Sauce mapRowtoSauce(SqlRowSet rs) {
        Sauce sauce = new Sauce();

        sauce.setId(rs.getInt("sauce_id"));
        sauce.setName(rs.getString("sauce_name"));
        sauce.setPrice(rs.getBigDecimal("sauce_price"));

        return sauce;
    }

    private Size mapRowtoSize(SqlRowSet rs) {
        Size size = new Size();

        size.setId(rs.getInt("size_id"));
        size.setName(rs.getString("size_name"));
        size.setPrice(rs.getBigDecimal("size_price"));

        return size;
    }

    public Customer mapRowToCustomer(SqlRowSet rs) {
        Customer customer = new Customer();

        customer.setId(rs.getInt("customer_id"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setAddress1(rs.getString("address_1"));
        customer.setAddress2(rs.getString("address_2"));
        customer.setCity(rs.getString("city"));
        customer.setState(rs.getString("state"));
        customer.setZipcode(rs.getInt("zip_code"));
        customer.setEmail(rs.getString("email"));

        return customer;
    }


    // Elements Methods

    public Crust getCrustByCrustId(int crustId) {
        Crust crust = null;

        String sql = "SELECT * FROM crust WHERE crust_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, crustId);

        if (results.next()) {
            crust = mapRowtoCrust(results);
        }

        return crust;
    }

    public Sauce getSauceBySauceId(int sauceId) {
        Sauce sauce = null;

        String sql = "SELECT * FROM sauce WHERE sauce_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, sauceId);

        if (results.next()) {
            sauce = mapRowtoSauce(results);
        }

        return sauce;
    }

    public Size getSizeBySizeId(int sizeId) {

        Size size = null;
        String sql = "SELECT * FROM size WHERE size_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, sizeId);

        if (results.next()) {
            size = mapRowtoSize(results);
        }

        return size;
    }

    // Customer & Order Methods

    public Customer createCustomer(Customer newCustomer) {

        String sql = "INSERT INTO customers (first_name, last_name, address_1, address_2, city, state, zip_code, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING customer_id;";

        int newCustomerId = jdbcTemplate.queryForObject(sql, Integer.class, newCustomer.getFirstName(), newCustomer.getLastName(), newCustomer.getAddress1(), newCustomer.getAddress2(), newCustomer.getCity(), newCustomer.getState(), newCustomer.getZipcode(), newCustomer.getEmail());

        return getCustomerById(newCustomerId);
    }

    public Customer getCustomerById(int customerId) {
        Customer customer = null;
        String sql = "SELECT * FROM customers WHERE customer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, customerId);
        if (results.next()) {
            customer = mapRowToCustomer(results);
        }
        return customer;
    }

    public Order createOrder(Order newOrder) {

        String ccSql = "INSERT INTO credit_cards (credit_card_number, expiration_date, cvv) VALUES (?, ?, ?)";
        //TODO: Prepare for saved cards
        jdbcTemplate.update(ccSql, newOrder.getCreditCardNumber(), newOrder.getExpirationDate(), newOrder.getCvv());

        String sql = "INSERT INTO orders (order_date, order_type, status, is_ASAP, pickup_time, customer_id, credit_card_number) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING order_id";

        int newOrderId = jdbcTemplate.queryForObject(sql, Integer.class, newOrder.getOrderDate(), newOrder.getOrderType(), "Pending", newOrder.isASAP(), newOrder.getPickupTime(), newOrder.getCustomerId(), newOrder.getCreditCardNumber());

        addPizzasToOrder(newOrderId, newOrder.getPizzas());

        return getOrderById(newOrderId);
    }

    public void addPizzasToOrder(int orderId,List<Pizza> orderPizzas){
            String itemSql = "INSERT INTO order_item (order_id, pizza_id, price) VALUES (?, ?, ?);";
            for(Pizza pizza : orderPizzas){
                BigDecimal price = calculatePrice(pizza.getId());
                jdbcTemplate.update(itemSql,orderId,pizza.getId(), price);
        }
    }

    public Order getOrderById(int orderId) {
        Order order = null;
        String sql = "SELECT * FROM orders WHERE order_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, orderId);
        if (results.next()) {
            order = mapRowtoOrder(results);
        }
        return order;
    }

    public Order mapRowtoOrder(SqlRowSet rs) {
        Order order = new Order();

        order.setId(rs.getInt("order_id"));
        order.setOrderDate(rs.getDate("order_date"));
        order.setOrderType(rs.getString("order_type"));
        order.setStatus(rs.getString("status"));
        order.setIsASAP(rs.getBoolean("is_ASAP"));
        Time pickupTime = rs.getTime("pickup_time");
        if (pickupTime != null) {
            order.setPickupTime(pickupTime.toLocalTime());
        } else {
            order.setPickupTime(null); // Explicitly set to null (optional, since it is the default)
        }        order.setCustomerId(rs.getInt("customer_id"));
        order.setCreditCardNumber(rs.getString("credit_card_number"));

        return order;
    }



}



