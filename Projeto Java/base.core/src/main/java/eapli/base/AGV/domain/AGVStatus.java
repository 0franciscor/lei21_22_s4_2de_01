package eapli.base.AGV.domain;

public enum AGVStatus {
    FREE("Is Free"),
    CHARGING("Is Charging"),
    OCCUPIED_SERVING("Busy preparing an order"),
    GIVEN_ORDER("Given Order"),
    MAINTENANCE("In Maintenance");

    private String agvStatus;

    AGVStatus(String agvStatus) {
        this.agvStatus = agvStatus;
    }


    @Override
    public String toString() {
        return agvStatus;
    }
}

