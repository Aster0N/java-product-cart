package services;

import classes.Product;
import services.db.DatabaseService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class ProductService {
    public List<Product> loadProductListFromDB() {
        List<Product> products = new ArrayList<Product>();

        DatabaseService dbService = new DatabaseService();
        String sql = "select * from product_list;";
        ResultSet resultSet = dbService.select(sql);
        try {
            while (resultSet.next()) {
                String uId = resultSet.getString("uid");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                boolean savedInCart = resultSet.getBoolean("saved_in_cart");
                float price = resultSet.getFloat("price");
                Product product = new Product(uId, name, description, savedInCart, price);
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return products;
    }
    public void addProduct(Product product) {
        DatabaseService dbService = new DatabaseService();
        String sql = "insert into product_list (uid, name, description, saved_in_cart, price) values ('"
                + product.getUId() + "','"
                + product.getName() + "','"
                + product.getDescription() + "',"
                + product.getSavedInCart() + ",'"
                + product.getPrice() + "');";

        if(dbService.update(sql)) {
            System.out.println("Data inserted successfully");
        } else {
            System.out.println("DB update(insert) error");
        }
    }

    public void saveProduct(boolean isSaved, String uId) {
        DatabaseService dbService = new DatabaseService();
        String sql = "update product_list set saved_in_cart=" + isSaved + " where uid='" + uId + "';";

        if(dbService.update(sql)) {
            System.out.println("Product "+ uId + " saved successfully");
        } else {
            System.out.println("DB update(save) error");
        }
    }
}
