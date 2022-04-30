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
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AGVId)) {
            return false;
        }

        final AGVId that = (AGVId) o;
        return this.id.equals(that.id);
    }

    @Override
    public int compareTo(final AGVId arg) {
        return id.compareTo(arg.id);
    }
}