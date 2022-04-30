package eapli.base.warehouse.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

@Embeddable
public class BinId implements ValueObject, Comparable<BinId> {

    private Long Id;

    @OneToOne(optional = false, cascade = CascadeType.MERGE)
    private Shelf shelf;

    public BinId(){}

    public BinId(final Long Id, final Shelf shelf){
        this.Id = Id;
        this.shelf = shelf;
    }

    @Override
    public int compareTo(BinId o) {
        if(Id == o.Id && shelf.sameAs(o.shelf)) return 0;
        return -1;
    }
}
