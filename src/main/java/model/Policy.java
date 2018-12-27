package model;

import model.enums.Status;

import java.util.Date;

/**
 * Created by kreisso on 02.11.2018.
 */
public class Policy {
    private long id;
    private Status status;
    private Date beginning;
    private Date ending;
    private int ukk;
    private int agentId;
    private Offer offer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getBeginning() {
        return beginning;
    }

    public void setBeginning(Date beginning) {
        this.beginning = beginning;
    }

    public Date getEnding() {
        return ending;
    }

    public void setEnding(Date ending) {
        this.ending = ending;
    }

    public int getUkk() {
        return ukk;
    }

    public void setUkk(int ukk) {
        this.ukk = ukk;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public String[] infoForTable(int i){
        String[] policyInfo = new String[4];
        policyInfo[0] = String.valueOf(i);
        policyInfo[1] = String.valueOf(getId());
        policyInfo[2] = String.valueOf(getOffer().getType());
        policyInfo[3] = String.valueOf(getStatus());
        return policyInfo;
    }
    public String[] infoForTableAgent(int i){
        String[] policyInfo = new String[7];
        policyInfo[0] = String.valueOf(i);
        policyInfo[1] = String.valueOf(getId());
        policyInfo[2] = String.valueOf(getUkk());
        policyInfo[3] = String.valueOf(getOffer().getType());
        policyInfo[4] = String.valueOf(getBeginning());
        policyInfo[5] = String.valueOf(getEnding());
        policyInfo[6] = String.valueOf(getStatus());
        return policyInfo;
    }
}
