package eapli.base.warehouse.domain;

public class AisleBuilder {

    private Aisle aisle;

    private Long Id;

    private Warehouse warehouse;

    private Accessibility accessibility;

    private Depth depth;

    private Begin begin;

    private End end;

    public AisleBuilder(){}

    public AisleBuilder withId(final Long Id){
        this.Id = Id;
        return this;
    }

    public AisleBuilder withWarehouse(final Warehouse warehouse){
        this.warehouse = warehouse;
        return this;
    }

    public AisleBuilder withAccessibility(final Accessibility accessibility){
        this.accessibility = accessibility;
        return this;
    }

    public AisleBuilder withDepth(final Depth depth){
        this.depth = depth;
        return this;
    }

    public AisleBuilder withBegin(final Begin begin){
        this.begin = begin;
        return this;
    }

    public AisleBuilder withEnd(final End end){
        this.end = end;
        return this;
    }

    private Aisle buildAisle(){
        if(aisle != null)
            return aisle;
        if(accessibility != null && depth != null && begin != null && end != null)
            this.aisle = new Aisle(Id, warehouse, accessibility, depth, begin, end);

        return aisle;
    }

    public Aisle build() {
        final Aisle returnAisle = buildAisle();
        return returnAisle;
    }
}
