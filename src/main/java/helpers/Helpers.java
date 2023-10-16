package helpers;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.io.InputStream;

import classes.Product;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Helpers {
    public static void fillProductList(List<Product> list) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        InputStream jsonStream = Helpers.class.getClassLoader().getResourceAsStream("productList.json");
        JsonNode rootNode = objectMapper.readTree(jsonStream);


        JsonNode productsNode = rootNode.get("products");
        if (productsNode.isArray()) {
            for (JsonNode productNode : productsNode) {
                String name = productNode.get("name").asText();
                String description = productNode.get("description").asText  ();
                float price = (float) productNode.get("price").asDouble();
                String imageUrl = productNode.get("imageUrl").asText();

                Product product = new Product(name, description, price, imageUrl);
                list.add(product);
            }
        }
    }
}