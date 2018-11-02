package Model;

/**
 * Created by kreisso on 02.11.2018.
 */
public class Client extends User {

    private int ukk;
    private String pesel;
    private String firstName;
    private String lastName;
    private Contact contact;
    private Policy[] policies;
    private String message;

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
}
