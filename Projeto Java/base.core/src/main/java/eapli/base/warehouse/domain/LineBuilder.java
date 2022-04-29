package eapli.base.warehouse.domain;
public class LineBuilder {

    private Line line;

    private Long Id;

    private Aisle aisle;

    private Begin begin;

    private End end;

    private int numShelves;

    public LineBuilder(final Long Id, final Aisle aisle, final Begin begin, final End end, final int numShelves){
        this.Id = Id;
        this.aisle = aisle;
        this.begin = begin;
        this.end = end;
        this.numShelves = numShelves;

    }
    private Line buildLine(){
        if(line != null)
            return line;
        else if(begin != null && end != null) {
            this.line = new Line(Id, aisle, begin, end, numShelves);
            return line;
        }
        return null;
    }

    public Line build(){
        final Line returnLine = buildLine();
        return returnLine;
    }
}
