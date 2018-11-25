package model;

/**
 * Created by kreisso on 02.11.2018.
 */
public class Multiagency {
    private int id;
    private String name;
    private Contact contact;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String[] infoForTable(int i){
        String[] multiagencies = new String[3];
        multiagencies[0] = String.valueOf(i);
        multiagencies[1] = String.valueOf(this.contact.getCity());
        multiagencies[2] = String.valueOf(this.contact.getStreetAndNo());
        return multiagencies;
    }

}