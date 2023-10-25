package classes;

import java.util.UUID;

public class Product {
    private String uId;
    private String name;
    private String description;
    private float price;
    private boolean savedInCart = false;

    public Product(String name, String description, float price) {
        this.uId = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
    }
    public Product(String uId, String name, String description, boolean savedInCart, float price) {
        this.uId = uId;
        this.name = name;
        this.description = description;
        this.savedInCart = savedInCart;
        this.price = price;
    }

    public String getUId() {
        return uId;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public float getPrice() {
        return price;
    }
    public boolean getSavedInCart() {
        return savedInCart;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDesctiption(String description) {
        this.description = description;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public void setSavedInCart(boolean savedInCart) {
        this.savedInCart = savedInCart;
    }

    public void printProductInfo() {
        System.out.println(this.uId);
        System.out.println(this.name);
        System.out.println(this.description);
        System.out.println(this.price);
        System.out.println(this.savedInCart);
    }
}
