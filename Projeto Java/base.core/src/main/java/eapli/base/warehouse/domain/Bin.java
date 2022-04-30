package eapli.base.warehouse.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Bin implements AggregateRoot<Long> {

    @Id
    private Long Id;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    private Shelf shelf;

    public Bin(){}

    public Bin(final Long Id, final Shelf shelf) {
        this.Id = Id;
        this.shelf = shelf;
    }

    @Override
    public boolean sameAs(Object other) {
        if(other == null) return false;

        if(this == other) return true;

        Bin newObj = ((Bin) other);

        return Id == Id && shelf == newObj.shelf;
    }

    @Override
    public Long identity() {
        return Id;
    }
}
