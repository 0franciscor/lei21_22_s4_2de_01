package eapli.base.AGVManagement.Domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class AGVTask implements ValueObject {
    private String agvTask;

    public AGVTask(String id) {
        this.agvTask = id;
    }
    public AGVTask() {}

    public String getAgvTask() {
        return agvTask;
    }

    public void setAgvTask(String agvTask) {
        this.agvTask = agvTask;
    }

    @Override
    public String toString() {
        return "AGVTask{" +
                "id='" + agvTask + '\'' +
                '}';
    }
}
