package RestAssured;

public class Product {
    public String title;
    public double price;
    public String description;
    public String image;
    public String category;

    // Standard constructor
    public Product(String title, double price, String description, String image, String category) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.image = image;
        this.category = category;
    }
}
