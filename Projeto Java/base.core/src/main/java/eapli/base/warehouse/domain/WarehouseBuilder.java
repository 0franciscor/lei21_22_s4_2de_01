package eapli.base.warehouse.domain;

public class WarehouseBuilder {

    private Warehouse warehouse;

    private Long Id;

    private WarehousePlant warehousePlant;

    private Dashboard dashboard;

    public WarehouseBuilder(){}
    public WarehouseBuilder(final Long Id, final WarehousePlant warehousePlant, final Dashboard dashboard){
        this.Id = Id;
        this.warehousePlant = warehousePlant;
        this.dashboard = dashboard;
    }

    private Warehouse buildWarehouse(){
        if(warehouse != null)
            return warehouse;
        if(warehousePlant != null && dashboard != null)
            this.warehouse = new Warehouse(Id, warehousePlant, dashboard);

        return warehouse;
    }

    public Warehouse build(){
        final Warehouse returnWarehouse = buildWarehouse();
        return returnWarehouse;
    }

}
