package model;

/**
 * Created by kreisso on 02.11.2018.
 */
public class Contact {

    private String city;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String streetAndNo;
    private String postCode;
    private String phoneNumber;
    private String email;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetAndNo() {
        return streetAndNo;
    }

    public void setStreetAndNo(String streetAndNo) {
        this.streetAndNo = streetAndNo;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
