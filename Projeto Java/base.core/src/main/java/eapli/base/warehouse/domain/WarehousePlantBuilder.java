package eapli.base.warehouse.domain;

import java.util.List;

public class WarehousePlantBuilder {

    private WarehousePlant warehousePlant;
    private String description;

    private int length;

    private int width;

    private int square;

    private String unit;

    private List<AGVDock> dockList;

    private List<Aisle> aisleList;

    public WarehousePlantBuilder(final String description, final int length, final int width, final int square, final String unit,
                                 final List<AGVDock> dockList, final List<Aisle> aisleList){
        this.description = description;
        this.length = length;
        this.width = width;
        this.square = square;
        this.unit = unit;
        this.dockList = dockList;
        this.aisleList = aisleList;
    }

    private WarehousePlant buildWarehousePlant(){
        if(warehousePlant != null)
            return warehousePlant;
        if(description != null && unit != null && dockList != null && aisleList != null)
            this.warehousePlant = new WarehousePlant(description, length, width, square, unit, dockList, aisleList);

        return warehousePlant;
    }

    public WarehousePlant buildWarehouse(){
        final WarehousePlant returnPlant = buildWarehousePlant();
        return returnPlant;
    }
}