package services;

import classes.Product;
import services.db.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.PreparedStatement;

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
        String sql = "select * from product_list where user_uid='"+ userUId + "' or user_uid='all';";
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
        String sql = "insert into product_list (uid, name, description, is_favorite, price, user_uid) values ('"
                + product.getUId() + "','"
                + product.getName() + "','"
                + product.getDescription() + "',"
                + product.getIsFavorite() + ",'"
                + product.getPrice() + "', '"
                + product.getUserUId() + "');";

        if(dbService.update(sql)) {
            System.out.println("Data inserted successfully");
        } else {
            System.out.println("DB update(insert) error");
        }
    }

    public void deleteProduct(String productToDelete) {
        DatabaseService dbService = new DatabaseService();
        String sql = "delete from product_list where uid='" + productToDelete + "';";

        if(dbService.update(sql)) {
            System.out.println("Product "+ productToDelete + " deleted successfully");
        } else {
            System.out.println("DB product "+ productToDelete +" delete error");
        }
    }

    public void updateProductBooleanFieldById(String fieldName, boolean fieldValue, String uId, String userUId) {
        DatabaseService dbService = new DatabaseService();
        String sql = "update product_list set " + fieldName + "=" + fieldValue + ", " +
                "user_uid='" + userUId + "' where uid='" + uId + "';";

        if(dbService.update(sql)) {
            System.out.println("Product "+ uId + " " + fieldName + " changed successfully");
        } else {
            System.out.println("DB update "+ fieldName +" error");
        }
    }

    public void orderProducts(List<Product> products, String userUId) {
        List<String> productIds = new ArrayList<String>();
        DatabaseService dbService = new DatabaseService();
        Connection conn = dbService.getConnect();

        for (Product product : products) {
            productIds.add(product.getUId());
        }

        try {
            // remove products from product_cart
            String sql = "update product_list set is_in_cart=false where uid = any(?);"+
                        "insert into order_history " +
                        "(product_id, user_uid) select id, user_uid from " +
                        "product_list pl where pl.uid = any(?) and user_uid=?;";
            PreparedStatement updateStatement = conn.prepareStatement(sql);
            updateStatement.setArray(1, conn.createArrayOf("varchar", productIds.toArray()));
            updateStatement.setArray(2, conn.createArrayOf("varchar", productIds.toArray()));
            updateStatement.setString(3, userUId);
            updateStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
