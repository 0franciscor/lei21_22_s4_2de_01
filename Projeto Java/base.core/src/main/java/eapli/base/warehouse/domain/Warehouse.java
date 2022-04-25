package eapli.base.warehouse.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;

@Entity
public class Warehouse implements AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Embedded
    private WarehousePlant warehousePlant;

    @Embedded
    private Dashboard dashboard;

    public Warehouse(){}

    public Warehouse(final WarehousePlant warehousePlant, final Dashboard dashboard){
        this.warehousePlant = warehousePlant;
        this.dashboard = dashboard;
    }

    @Override
    public boolean sameAs(Object other) {
        if(other == null) return false;
        if(this == other) return true;

        Warehouse newObj = ((Warehouse) other);

        return warehousePlant == newObj.warehousePlant && dashboard == newObj.dashboard;
    }

    @Override
    public Long identity() {
        return Id;
    }
}
