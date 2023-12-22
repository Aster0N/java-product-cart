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
                boolean isInCart = resultSet.getBoolean("is_in_cart");
                String userUId = resultSet.getString("user_uid");
                Product product = new Product(uId, name, description, isFavorite, isInCart, price, userUId);
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return products;
    }

    public List<Product> getUserProductListByUId(String userUId) {
        List<Product> products = new ArrayList<Product>();

        DatabaseService dbService = new DatabaseService();
        String sql = "select * from product_list where user_uid='"+ userUId + "';";
        ResultSet resultSet = dbService.select(sql);
        try {
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getString("uid"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getBoolean("is_favorite"),
                        resultSet.getBoolean("is_in_cart"),
                        resultSet.getFloat("price"),
                        resultSet.getString("user_uid")
                );
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
    public void updateProductBooleanFieldById(String fieldName, boolean fieldValue, String uId) {
        DatabaseService dbService = new DatabaseService();
        String sql = "update product_list set " + fieldName + "=" + fieldValue + " where uid='" + uId + "';";

        if(dbService.update(sql)) {
            System.out.println("Product "+ uId + " " + fieldName + " changed successfully");
        } else {
            System.out.println("DB update "+ fieldName +" error");
        }
    }
}
