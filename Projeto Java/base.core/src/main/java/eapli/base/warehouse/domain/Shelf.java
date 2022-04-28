package eapli.base.warehouse.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;

@Entity
public class Shelf implements AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private boolean isEmpty;

    //@Embedded
    //private Bin bin;

    public Shelf(){}

    public Shelf(final boolean isEmpty){
        this.isEmpty = isEmpty;
    }

    @Override
    public boolean sameAs(Object other) {
        if(other == null) return false;
        if(this == other) return true;

        Shelf otherShelf = ((Shelf) other);

        return Id == otherShelf.Id && isEmpty == isEmpty;
    }

    @Override
    public Long identity() {
        return Id;
    }
}
