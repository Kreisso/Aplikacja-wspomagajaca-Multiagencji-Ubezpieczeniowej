package model;

import model.enums.Type;

/**
 * Created by kreisso on 02.11.2018.
 */
public class Offer {
    private String name;
    private Type type;
    private double price;
    private String description;

    public Offer() {
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
