BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');

INSERT INTO crust (crust_name, crust_price) VALUES
('Regular', 0.00),
('Thin', 0.00),
('Thick', 1.00),
('Gluten-Free', 2.00),
('Stuffed', 2.00),
('Cauliflower', 1.00);

COMMIT TRANSACTION;
