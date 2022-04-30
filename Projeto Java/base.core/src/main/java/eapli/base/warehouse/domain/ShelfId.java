package eapli.base.warehouse.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.*;

@Embeddable
public class ShelfId implements ValueObject, Comparable<ShelfId> {

    private Long Id;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    private Line line;

    public ShelfId(){}

    public ShelfId(final Long Id, final Line line){
        this.Id = Id;
        this.line = line;
    }

    @Override
    public int compareTo(ShelfId o) {
        if(Id == o.Id && line.sameAs(o.line)) return 0;
        return -1;
    }
}
