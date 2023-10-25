package classes;

public class Product {
    private String name;
    private String description;
    private float price;
    private boolean savedInCart = false;

    public Product(String name, String description, float price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
    public Product(String name, String description, boolean savedInCart, float price) {
        this.name = name;
        this.description = description;
        this.savedInCart = savedInCart;
        this.price = price;
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
        System.out.println(this.name);
        System.out.println(this.description);
        System.out.println(this.price);
        System.out.println(this.savedInCart);
    }
}
