package Model;

/**
 * Created by kreisso on 02.11.2018.
 */
public class Contact {

    private String city;
    private String street;
    private int buildingNumber;
    private String postCode;
    private String phoneNumber;
    private String email;

    public Contact(String city, String street, int buildingNumber, String postCode, String phoneNumber, String email) {
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.postCode = postCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
