package eapli.base.warehouse.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;

@Entity
public class AGVDock implements AggregateRoot<String> {

    @Id
    private String Id;
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    private Warehouse warehouse;
    @Embedded
    private Depth depth;

    @Embedded
    private Accessibility accessibility;

    @Embedded
    private Begin begin;

    @Embedded
    private End end;

    public AGVDock(){}

    public AGVDock(final String Id, final Warehouse warehouse, final Depth depth, final Accessibility accessibility, final Begin begin, final End end){
        this.Id = Id;
        this.warehouse = warehouse;
        this.depth = depth;
        this.accessibility = accessibility;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public boolean sameAs(Object other) {
        if(other == null) return false;
        if(this == other) return true;

        AGVDock newObj = ((AGVDock) other);

        return warehouse == newObj.warehouse && depth == newObj.depth && accessibility == newObj.accessibility && begin == newObj.begin && end == newObj.end;
    }

    @Override
    public String identity() {
        return Id;
    }

    public String getId() {
        return Id;
    }
}
