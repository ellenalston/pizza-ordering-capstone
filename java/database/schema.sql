BEGIN TRANSACTION;

DROP TABLE IF EXISTS pizza_topping;
DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS pizza;
DROP TABLE IF EXISTS topping;
DROP TABLE IF EXISTS sauce;
DROP TABLE IF EXISTS crust;
DROP TABLE IF EXISTS size;
DROP TABLE IF EXISTS discount;
DROP TABLE IF EXISTS credit_cards;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);
CREATE TABLE customers (
	customer_id SERIAL,
	first_name VARCHAR,
	last_name VARCHAR,
	address_1 VARCHAR,
	address_2 VARCHAR,
	city VARCHAR,
	state VARCHAR,
	zip_code INT,
	email VARCHAR,
	CONSTRAINT pk_customers PRIMARY KEY (customer_id)
);

CREATE TABLE credit_cards(
	credit_card_number VARCHAR(16),
	expiration_date CHAR(4),
	cvv CHAR(3),
	CONSTRAINT pk_credit_card PRIMARY KEY(credit_card_number)

);

CREATE TABLE orders (
	order_id SERIAL,
	order_date TIMESTAMP,
	order_type VARCHAR(255),
	status VARCHAR(255),
	is_ASAP BOOLEAN,
	pickup_time TIME,
	customer_id INT,
	credit_card_number VARCHAR (16),
	CONSTRAINT pk_orders PRIMARY KEY(order_id),
	CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customers (customer_id),
	CONSTRAINT fk_credit FOREIGN KEY (credit_card_number) REFERENCES credit_cards (credit_card_number)
);



CREATE TABLE discount(
	discount_id SERIAL,
	discount_code VARCHAR,
	percentage DECIMAL,
	CONSTRAINT pk_discount_id PRIMARY KEY (discount_id)
);

CREATE TABLE size (
	size_id SERIAL,
	size_name VARCHAR,
	size_price NUMERIC (4,2),
	CONSTRAINT pk_size_id PRIMARY KEY (size_id)
);

CREATE TABLE crust(
	crust_id serial,
	crust_name VARCHAR,
	crust_price numeric (4,2),
	CONSTRAINT pk_crust PRIMARY KEY (crust_id)
);

CREATE TABLE sauce (
	sauce_id SERIAL,
	sauce_name VARCHAR,
	sauce_price NUMERIC (4,2),
	CONSTRAINT pk_sauce_id PRIMARY KEY (sauce_id)
);

CREATE TABLE topping (
	topping_id serial,
	topping_name VARCHAR,
	topping_type VARCHAR,
	topping_price numeric (4,2),
	is_available boolean,
	CONSTRAINT pk_topping_id PRIMARY KEY (topping_id)
);

CREATE TABLE pizza (
	pizza_id serial,
	specialty_name VARCHAR,
	size_id int,
	crust_id int,
	sauce_id int,
	is_available boolean,
	discount_id int,
	special_desc VARCHAR (500),
	CONSTRAINT pk_pizza PRIMARY KEY (pizza_id),
	CONSTRAINT fk_size FOREIGN KEY (size_id) REFERENCES size (size_id),
	CONSTRAINT fk_crust FOREIGN KEY (crust_id) REFERENCES crust (crust_id),
	CONSTRAINT fk_sauce FOREIGN KEY (sauce_id) REFERENCES sauce (sauce_id),
	CONSTRAINT fk_discount FOREIGN KEY (discount_id) REFERENCES discount (discount_id)
);

CREATE TABLE order_item (
	order_id INT,
	pizza_id INT,
	price NUMERIC (4,2),
	CONSTRAINT pk_order_item PRIMARY KEY (order_id, pizza_id),
	CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders (order_id),
	CONSTRAINT fk_pizza FOREIGN KEY (pizza_id) REFERENCES pizza (pizza_id)
);

CREATE TABLE pizza_topping (
	pizza_id INT,
	topping_id INT,
	CONSTRAINT pk_pizza_topping PRIMARY KEY(pizza_id, topping_id),
	CONSTRAINT fk_pizza FOREIGN KEY (pizza_id) REFERENCES pizza (pizza_id),
	CONSTRAINT fk_topping FOREIGN KEY (topping_id) REFERENCES topping (topping_id)
);

COMMIT TRANSACTION;
