package classes;

public class Product {
    private String name;
    private String description;
    private float price;

    public Product(String name, String description, float price, String imageUrl) {
        this.name = name;
        this.description = description;
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

    public void setName(String name) {
        this.name = name;
    }
    public void setDesctiption(String description) {
        this.description = description;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    public void printProductInfo() {
        System.out.println(this.name);
        System.out.println(this.description);
        System.out.println(this.price);
    }
}
