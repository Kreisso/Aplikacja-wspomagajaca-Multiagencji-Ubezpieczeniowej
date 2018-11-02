package model;

/**
 * Created by kreisso on 02.11.2018.
 */
public class Offer {
    private String name;
    private Type type;
    private double price;
    private String description;

    public Offer(String name, Type type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.description = "Example description";
    }

    public Offer(String name, Type type, double price, String description) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.description = description;
    }
}
