package eapli.base.AGV.Domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class ChangeAGVStatus implements ValueObject {

    public String status;

    public ChangeAGVStatus(String status) {
        if (!status.equalsIgnoreCase(AGVStatus.FREE.name()) && !status.equalsIgnoreCase(AGVStatus.CHARGING.name())
        && !status.equalsIgnoreCase(AGVStatus.OCCUPIED_SERVING.name()) && !status.equalsIgnoreCase(AGVStatus.GIVEN_ORDER.name())
        && !status.equalsIgnoreCase(AGVStatus.MAINTENANCE.name())){
            throw new IllegalArgumentException("Invalid Status");
        }
        this.status = status;
    }
    public ChangeAGVStatus() {}


}
