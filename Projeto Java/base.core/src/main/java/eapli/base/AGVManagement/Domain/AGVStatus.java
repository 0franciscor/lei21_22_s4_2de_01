package eapli.base.AGVManagement.Domain;

public enum AGVStatus {
    FREE("Is Free"),
    CHARGING("Is Charging"),
    OCCUPIED_SERVING("Is Occupied Serving"),
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

