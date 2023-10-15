package classes;

public class Product {
    private String name;
    private String desctiption;
    private String price;

    public Product(String name, String desctiption, String price) {
        this.name = name;
        this.desctiption = desctiption;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public String getDesctiption() {
        return desctiption;
    }
    public String getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDesctiption(String desctiption) {
        this.desctiption = desctiption;
    }
    public void setPrice(String price) {
        this.price = price;
    }
}
