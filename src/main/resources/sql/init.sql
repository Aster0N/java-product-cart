CREATE TABLE IF NOT EXISTS product_list (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    saved_in_cart BOOLEAN DEFAULT false,
    price VARCHAR(255)
);
