package eapli.base.warehouse.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;
import java.util.List;

@Entity
public class Line implements AggregateRoot<Long> {

    @Id
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

    public Line(){}

    public Line(final List<Shelf> shelfSet){
        this.shelfSet = shelfSet;
    }

    public Line(final Long Id, final Begin begin, final End end, final int numShelves){
        this.Id = Id;
        this.begin = begin;
        this.end = end;
        this.numShelves = numShelves;
    }

    @Override
    public boolean sameAs(Object other) {
        if(other == null) return false;
        if(this == other) return true;

        Line newLine = ((Line) other);

        return Id == newLine.Id && shelfSet == newLine.shelfSet && begin == newLine.begin && end == newLine.end && numShelves == newLine.numShelves;
    }

    @Override
    public Long identity() {
        return Id;
    }
}
