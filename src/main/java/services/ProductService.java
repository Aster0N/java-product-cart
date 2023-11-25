package services;

import classes.Product;
import services.db.DatabaseService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class ProductService {
    public List<Product> loadProductListFromDB(String sql) {
        List<Product> products = new ArrayList<Product>();

        DatabaseService dbService = new DatabaseService();
        ResultSet resultSet = dbService.select(sql);
        try {
            while (resultSet.next()) {
                String uId = resultSet.getString("uid");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                boolean isFavorite = resultSet.getBoolean("is_favorite");
                float price = resultSet.getFloat("price");
                Product product = new Product(uId, name, description, isFavorite, price);
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return products;
    }
    public void addProduct(Product product) {
        DatabaseService dbService = new DatabaseService();
        String sql = "insert into product_list (uid, name, description, is_favorite, price) values ('"
                + product.getUId() + "','"
                + product.getName() + "','"
                + product.getDescription() + "',"
                + product.getIsFavorite() + ",'"
                + product.getPrice() + "');";

        if(dbService.update(sql)) {
            System.out.println("Data inserted successfully");
        } else {
            System.out.println("DB update(insert) error");
        }
    }
    public void addProductToFavorite(boolean isFavorite, String uId) {
        DatabaseService dbService = new DatabaseService();
        String sql = "update product_list set is_favorite=" + isFavorite + " where uid='" + uId + "';";

        if(dbService.update(sql)) {
            System.out.println("Product "+ uId + " is_favorite changed successfully");
        } else {
            System.out.println("DB update is_favorite error");
        }
    }
}
