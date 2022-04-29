package eapli.base.warehouse.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;

@Entity
public class Shelf implements AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    private Aisle aisle;

    private boolean isEmpty;

    //@Embedded
    //private Bin bin;

    public Shelf(){}

    public Shelf(final Aisle aisle, final boolean isEmpty){
        this.aisle = aisle;
        this.isEmpty = isEmpty;
    }

    @Override
    public boolean sameAs(Object other) {
        if(other == null) return false;
        if(this == other) return true;

        Shelf otherShelf = ((Shelf) other);

        return Id == otherShelf.Id && aisle == otherShelf.aisle && isEmpty == isEmpty;
    }

    @Override
    public Long identity() {
        return Id;
    }
}
