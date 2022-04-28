package eapli.base.warehouse.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;

@Entity
public class Warehouse implements AggregateRoot<Long> {

    @Id
    private Long Id;

    @OneToOne
    private WarehousePlant warehousePlant;

    @Embedded
    private Dashboard dashboard;

    public Warehouse(){}

    public Warehouse(final WarehousePlant warehousePlant){
        this.warehousePlant = warehousePlant;
    }

    public Warehouse(final Long Id, final WarehousePlant warehousePlant, final Dashboard dashboard){
        this.Id = Id;
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
