package model;

import model.enums.Type;

public class Offer {
    private int id;
    private String name;
    private Type type;
    private double price;
    private String description;
    private String companyName;

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getOfferToDisplay(){
        String[] toReturn = new String[3];
        toReturn[0] = this.companyName;
        toReturn[1] = this.name;
        toReturn[2] = this.description;
        return toReturn;
    }
}
