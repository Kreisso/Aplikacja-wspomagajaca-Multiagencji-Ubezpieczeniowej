package Model;

/**
 * Created by kreisso on 02.11.2018.
 */
public class Agent extends User {
    private int id;
    private int agencyId;
    private Client[] clients;

    public Agent(String login, String password, int id, int agencyId, Client[] clients) {
        super(login, password);
        this.id = id;
        this.agencyId = agencyId;
        this.clients = clients;
    }
}
