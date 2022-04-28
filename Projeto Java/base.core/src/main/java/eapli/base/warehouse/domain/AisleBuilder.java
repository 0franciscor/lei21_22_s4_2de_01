package eapli.base.warehouse.domain;

import java.util.List;

public class AisleBuilder {

    private Aisle aisle;

    private Long Id;

    private Accessibility accessibility;

    private Depth depth;

    private Begin begin;

    private End end;

    private List<Line> lineList;

    public AisleBuilder(final Long Id, final Accessibility accessibility, final Depth depth, final Begin begin,
                        final End end, final List<Line> lineList){
        this.Id = Id;
        this.accessibility = accessibility;
        this.depth = depth;
        this.begin = begin;
        this.end = end;
        this.lineList = lineList;
    }

    private Aisle buildAisle(){
        if(aisle != null)
            return aisle;
        if(accessibility != null && depth != null && begin != null && end != null && lineList != null)
            this.aisle = new Aisle(Id, accessibility, depth, begin, end, lineList);

        return aisle;
    }

    public Aisle build() {
        final Aisle returnAisle = buildAisle();
        return returnAisle;
    }
}
