package model.Server;

import model.Policy;

import java.util.LinkedList;
import java.util.List;

public class SearchPolicy {
    private String id;
    private List<Policy> policies;

    public SearchPolicy(){
        policies = new LinkedList<Policy>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Policy> getPolicies() {
        return policies;
    }

    public void addPolicy(Policy policy) {
        policies.add(policy);
    }
}
