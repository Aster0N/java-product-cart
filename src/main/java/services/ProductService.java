package services;

import classes.Product;
import services.db.DatabaseService;

public class ProductService {
    public void addProduct(Product product) {
        DatabaseService dbService = new DatabaseService();
        String sql = "insert into product_list (name, description, saved_in_cart, price) values ("
                + product.getName() + ","
                + product.getDescription() + ","
                + product.getSavedInCart() + ","
                + product.getPrice() + ")";

        if(dbService.insert(sql)) {
            System.out.println("Data inserted successfully");
        } else {
            System.out.println("DB service insert error");
        }
    }
}
