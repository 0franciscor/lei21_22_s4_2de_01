package eapli.base.warehouse.domain;

public class AisleBuilder {

    private Aisle aisle;

    private Long Id;

    private Warehouse warehouse;

    private Accessibility accessibility;

    private Depth depth;

    private Begin begin;

    private End end;

    public AisleBuilder(final Long Id, final Warehouse warehouse, final Accessibility accessibility, final Depth depth, final Begin begin,
                        final End end){
        this.Id = Id;
        this.warehouse = warehouse;
        this.accessibility = accessibility;
        this.depth = depth;
        this.begin = begin;
        this.end = end;
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
