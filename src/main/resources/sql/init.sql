CREATE TABLE IF NOT EXISTS product_list (
    id serial PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    saved_in_cart BOOLEAN DEFAULT false,
    price VARCHAR(255)
);
