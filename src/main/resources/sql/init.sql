-- create table users
CREATE TABLE IF NOT EXISTS users (
    id serial PRIMARY KEY,
    uid VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
INSERT INTO users (uid, login, password) VALUES ('admin#1', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');

-- create table product_list
CREATE TABLE IF NOT EXISTS product_list (
    id serial PRIMARY KEY,
    user_uid VARCHAR(255) NOT NULL,
    uid VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    is_favorite BOOLEAN DEFAULT false,
    is_in_cart BOOLEAN DEFAULT false,
    price VARCHAR(255)
);

-- create table favorite_product
CREATE TABLE IF NOT EXISTS favorite_products (
    id serial PRIMARY KEY,
    product_id INTEGER REFERENCES product_list(id)
);

-- create function (that executes on trigger) which will add/remove a
-- field to/from favorite_products on updating is_favorite field in product_list
CREATE OR REPLACE FUNCTION update_favorite()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.is_favorite = true THEN
        INSERT INTO favorite_products (product_id) VALUES (NEW.id);
    ELSE
        DELETE FROM favorite_products WHERE product_id = NEW.id;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_favorite_trigger
AFTER UPDATE ON product_list
FOR EACH ROW
EXECUTE FUNCTION update_favorite();

-- create product_cart table
CREATE TABLE IF NOT EXISTS product_cart (
    id serial PRIMARY KEY,
    product_id INTEGER REFERENCES product_list(id)
);
-- create a function (that executes on trigger) which will add/remove a
-- field to/from product_cart on updating is_in_cart field in product_list
CREATE OR REPLACE FUNCTION update_cart()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.is_in_cart = true THEN
        INSERT INTO product_cart (product_id) VALUES (NEW.id);
    ELSE
        DELETE FROM product_cart WHERE product_id = NEW.id;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_cart_trigger
AFTER UPDATE ON product_list
FOR EACH ROW
EXECUTE FUNCTION update_cart();