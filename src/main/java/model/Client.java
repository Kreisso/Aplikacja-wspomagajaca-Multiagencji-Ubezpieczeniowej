package model;

/**
 * Created by kreisso on 02.11.2018.
 */
public class Client extends User {

    private int ukk;
    private String pesel;
    private String firstName;
    private String lastName;
    private Contact contact;
    private int agentId;
    private Policy[] policies;
    private String message;

    public Client(){

    }

    public Client(String login, String password, int ukk, String pesel, String firstName,
                  String lastName, Contact contact,
                  Policy[] policies, String message) {
        super(login, password);
        this.ukk = ukk;
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
        this.policies = policies;
        this.message = message;
    }

    public int getUkk() {
        return ukk;
    }

    public String getPesel() {
        return pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Contact getContact() {
        return contact;
    }

    public Policy[] getPolicies() {
        return policies;
    }

    public String getMessage() {
        return message;
    }

    public void setUkk(int ukk) {
        this.ukk = ukk;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public void setPolicies(Policy[] policies) {
        this.policies = policies;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }
}
