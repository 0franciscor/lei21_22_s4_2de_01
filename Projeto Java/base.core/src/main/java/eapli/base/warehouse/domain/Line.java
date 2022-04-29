package eapli.base.warehouse.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;
import java.util.List;

@Entity
public class Line implements AggregateRoot<Long> {

    @Id
    private Long Id;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    private Aisle aisle;

    @Embedded
    private Begin begin;

    @Embedded
    private End end;

    private int numShelves;

    //@Embedded
    //private Bin bin;

    public Line(){}
    public Line(final Long Id, final Aisle aisle, final Begin begin, final End end, final int numShelves){
        this.Id = Id;
        this.aisle = aisle;
        this.begin = begin;
        this.end = end;
        this.numShelves = numShelves;
    }

    @Override
    public boolean sameAs(Object other) {
        if(other == null) return false;
        if(this == other) return true;

        Line newLine = ((Line) other);

        return Id == newLine.Id && aisle == newLine.aisle && begin == newLine.begin && end == newLine.end && numShelves == newLine.numShelves;
    }

    @Override
    public Long identity() {
        return Id;
    }
}
