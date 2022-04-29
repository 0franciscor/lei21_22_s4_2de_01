package eapli.base.warehouse.domain;

import java.util.List;

public class WarehousePlantBuilder {

    private WarehousePlant warehousePlant;
    private String description;

    private int length;

    private int width;

    private int square;

    private String unit;

    public WarehousePlantBuilder(final String description, final int length, final int width, final int square, final String unit){
        this.description = description;
        this.length = length;
        this.width = width;
        this.square = square;
        this.unit = unit;
    }

    private WarehousePlant buildWarehousePlant(){
        if(warehousePlant != null)
            return warehousePlant;
        if(description != null && unit != null)
            this.warehousePlant = new WarehousePlant(description, length, width, square, unit);

        return warehousePlant;
    }

    public WarehousePlant buildWarehouse(){
        final WarehousePlant returnPlant = buildWarehousePlant();
        return returnPlant;
    }
}