BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO discount (discount_code, percentage) VALUES
('NONE', 0.0),
('SPECIALTY', 0.10),
('SPOOKY', 0.31),
('GRAD', 0.24);

INSERT INTO size (size_name, size_price) VALUES
('Small', 12.00),
('Medium', 15.00),
('Large', 18.00);

INSERT INTO crust (crust_name, crust_price) VALUES
('Regular', 0.00),
('Thin', 0.00),
('Thick', 1.00),
('Gluten-Free', 2.00),
('Stuffed', 2.00),
('Cauliflower', 1.00);


INSERT INTO sauce (sauce_name, sauce_price) VALUES
('Traditional Red', 0.00),
('Pesto', 2.00),
('White', 1.00),
('No Sauce', 0.00);

INSERT INTO topping (topping_name, topping_type, topping_price, is_available) VALUES
('Burrata Cheese', 'Premium', 2.00, TRUE),
('Blue Cheese', 'Premium', 2.00, TRUE),
('Roasted Peppers', 'Premium', 2.00, TRUE),
('Artichokes', 'Premium', 2.00, TRUE),
('Portabella Mushrooms', 'Premium', 2.00, TRUE),
('Pineapple', 'Premium', 2.00, TRUE),
('Italian Meatballs', 'Premium', 2.00, TRUE),
('Chicken', 'Premium', 2.00, TRUE),
('Bacon', 'Premium', 2.00, TRUE),
('Mozzarella Cheese', 'Regular', 0.00, TRUE),
('Pepperoni', 'Regular', 1.00, TRUE),
('Mushrooms', 'Regular', 1.00, TRUE),
('Onions', 'Regular', 1.00, TRUE),
('Sausage', 'Regular', 1.00, TRUE),
('Ham', 'Regular', 1.00, TRUE),
('Black Olives', 'Regular', 1.00, TRUE),
('Green Peppers', 'Regular', 1.00, TRUE),
('Tomatoes', 'Regular', 1.00, TRUE),
('Strawberries', 'Regular', 0.50, TRUE),
('Anchovies', 'Regular', 1.00, TRUE);

INSERT INTO pizza (specialty_name, size_id, crust_id, sauce_id, is_available, discount_id, special_desc) VALUES
('Array of Veggies', 2, 6, 2, TRUE, 2, 'Topped with burrata cheese for a soft touch and roasted peppers for a spicy kick, this flavor stack is sure to please. Artichokes add complexity, while onions keep it transparent. Black olives introduce a dark theme, and green peppers add freshness, with juicy tomatoes as the perfect sauce. This pizza is debugged and ready for deployment, ensuring every bite is a tasty success!'),
('Byte of the Beast', 3, 3, 1, TRUE, 2, 'This pie is packed with Italian meatballs for robust performance, chicken for a lightweight framework, and bacon for a crisp interface. Mozzarella holds it all together, while pepperoni brings the heat and sausage and ham ensure it''s fully loaded. Each bite is a meaty algorithm perfectly executed. Debug your hunger with this savory, error-free masterpiece!'),
('Compiling Error', 2, 2, 1, FALSE, 2, 'A pizza that’s as bold as refactoring legacy code! Salty anchovies meet sweet strawberries in a combo that’s unexpectedly delicious, proving that opposites really do compile. It’s the pizza that makes you wonder if it’s a bug or a feature—and then you realize it’s both!'),
('Primitive Cravings', 2, 2, 1, TRUE, 2, 'Featuring fresh mozzarella for a smooth user experience, tomatoes that deliver robust flavor with every bite, and basil for a refreshing update. All on a crisp crust that''s the perfect foundation for this simple yet elegant codebase. It''s a minimalist''s dream, optimized for taste with no extra frills—just pure, error-free deliciousness!'),
('Byte of Cheese', 2, 5, 3, FALSE, 2, 'A cheesy delight for software enthusiasts! With a fluffy stuffed crust and creamy white sauce for a solid foundation, it’s the perfect array of flavors. Topped with smooth mozzarella cheese and decadent burrata, this pizza also features tangy blue cheese to debug any blandness. A harmonious fusion of cheesy goodness awaits!');

INSERT INTO pizza_topping (pizza_id, topping_id) VALUES
(1, 1),(1, 3),(1, 4),(1, 13),(1, 16),(1, 17),(1, 18),
(2, 10),(2, 7),(2, 8),(2, 9),(2, 11),(2, 14),(2, 15),
(3, 19),(3, 20),(3, 2),
(4, 10),(4, 18),
(5, 10), (5,1), (5,2);

INSERT INTO customers (first_name, last_name, address_1, address_2, city, state, zip_code, email) VALUES
('Ellen', 'Alston', '123 Coding Lane', 'Apt 1', 'Cleveland', 'Ohio', 44236, 'ellenantes@gmail.com');

COMMIT TRANSACTION;
