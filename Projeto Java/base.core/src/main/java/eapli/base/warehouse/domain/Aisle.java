package eapli.base.warehouse.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;
import java.util.List;

@Entity
public class Aisle implements AggregateRoot<Long> {

    @Id
    private Long Id;

    @Embedded
    private Accessibility accessibility;

    @Embedded
    private Depth depth;

    @Embedded
    private Begin begin;

    @Embedded
    private End end;

    @ElementCollection
    private List<Line> lineList;

    public Aisle(){}

    public Aisle(final Long Id, final Accessibility accessibility, final Depth depth, final Begin begin,
                 final End end, final List<Line> lineList){
        this.Id = Id;
        this.accessibility = accessibility;
        this.depth = depth;
        this.begin = begin;
        this.end = end;
        this.lineList = lineList;
    }

    @Override
    public boolean sameAs(Object other) {
        if(other == null) return false;
        if(this == other) return true;

        Aisle newObj = ((Aisle) other);

        return accessibility == newObj.accessibility && depth == newObj.depth && begin == newObj.begin
                && end == newObj.end && lineList == newObj.lineList;
    }

    @Override
    public Long identity() {
        return Id;
    }
}
