package eapli.base.warehouse.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;
import java.util.List;

@Entity
@Embeddable
public class Row implements AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ElementCollection
    private List<Shelf> shelfSet;

    @Embedded
    private Begin begin;

    @Embedded
    private End end;

    private int numShelves;

    //@Embedded
    //private Bin bin;

    public Row(){}

    public Row(final List<Shelf> shelfSet){
        this.shelfSet = shelfSet;
    }

    public Row(final Long Id, final Begin begin, final End end, final int numShelves){
        this.Id = Id;
        this.begin = begin;
        this.end = end;
        this.numShelves = numShelves;
    }

    @Override
    public boolean sameAs(Object other) {
        if(other == null) return false;
        if(this == other) return true;

        Row newRow = ((Row) other);

        return Id == newRow.Id && shelfSet == newRow.shelfSet && begin == newRow.begin && end == newRow.end && numShelves == newRow.numShelves;
    }

    @Override
    public Long identity() {
        return Id;
    }
}
