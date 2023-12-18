package classes;

import java.util.UUID;

public class Product {
    private String uId;
    private String name;
    private String description;
    private float price;
    private boolean isFavorite = false;
    private boolean isInCart = false;

    public Product(String name, String description, float price) {
        this.uId = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
    }
    public Product(String uId, String name, String description, boolean isFavorite, boolean isInCart, float price) {
        this.uId = uId;
        this.name = name;
        this.description = description;
        this.isFavorite = isFavorite;
        this.isInCart = isInCart;
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
    public boolean getIsFavorite() {
        return isFavorite;
    }
    public boolean getIsInCart() { return isInCart; }

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
    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }
    public void setIsInCart(boolean isInCart) { this.isInCart = isInCart; }

    public void printProductInfo() {
        System.out.println(this.uId);
        System.out.println(this.name);
        System.out.println(this.description);
        System.out.println(this.price);
        System.out.println(this.isFavorite);
        System.out.println(this.isInCart);
    }
}
