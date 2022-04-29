package eapli.base.AGVManagement.Domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class AGVId implements ValueObject, Comparable<AGVId> {
    private String id;

    public AGVId(String id) {
        this.id = id;
    }

    public AGVId() {}

    public String getAGVId() {
        return id;
    }

    public void setAGVId(String agvId) {
        this.id = agvId;
    }

    @Override
    public String toString() {
        return "AGVId{" +
                "id=" + id +
                '}';
    }

    @Override
    public int compareTo(AGVId o) {
        return 0;
    }
}