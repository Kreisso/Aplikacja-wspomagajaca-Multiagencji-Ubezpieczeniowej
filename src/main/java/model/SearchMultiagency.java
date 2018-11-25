package model;

import java.util.List;

public class SearchMultiagency {
    private String city;
    private List<Multiagency> multiagencies;

    public List<Multiagency> getMultiagencies() {
        return multiagencies;
    }

    public void setMultiagencies(List<Multiagency> multiagencies) {
        this.multiagencies = multiagencies;
    }

    public SearchMultiagency(String city, List<Multiagency> multiagencies) {
        this.city = city;
        this.multiagencies=multiagencies;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
