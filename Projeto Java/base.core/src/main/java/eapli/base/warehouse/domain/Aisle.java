package eapli.base.warehouse.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;
import java.util.List;

@Entity
@Embeddable
public class Aisle implements AggregateRoot<Long> {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private List<Row> rowList;

    public Aisle(){}

    public Aisle(final Long Id, final Accessibility accessibility, final Depth depth, final Begin begin,
                 final End end, final List<Row> rowList){
        this.Id = Id;
        this.accessibility = accessibility;
        this.depth = depth;
        this.begin = begin;
        this.end = end;
        this.rowList = rowList;
    }

    @Override
    public boolean sameAs(Object other) {
        if(other == null) return false;
        if(this == other) return true;

        Aisle newObj = ((Aisle) other);

        return accessibility == newObj.accessibility && depth == newObj.depth && begin == newObj.begin
                && end == newObj.end && rowList == newObj.rowList;
    }

    @Override
    public Long identity() {
        return Id;
    }
}
