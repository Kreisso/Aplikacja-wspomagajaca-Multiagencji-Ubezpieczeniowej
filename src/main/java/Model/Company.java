package Model;

/**
 * Created by kreisso on 02.11.2018.
 */
public class Company {
    private String name;
    private  Offer[] offers;

    public Company(String name, Offer[] offers) {
        this.name = name;
        this.offers = offers;
    }
}
