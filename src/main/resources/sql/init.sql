-- create table product_list
CREATE TABLE IF NOT EXISTS product_list (
    id serial PRIMARY KEY,
    uid VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    is_favorite BOOLEAN DEFAULT false,
    price VARCHAR(255)
);

-- create table favorite_product
CREATE TABLE IF NOT EXISTS favorite_products (
    id serial PRIMARY KEY,
    product_id INTEGER REFERENCES product_list(id)
);

-- create function which updates favorite_products on updating is_favorite field in product_list
-- this function executes in trigger
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