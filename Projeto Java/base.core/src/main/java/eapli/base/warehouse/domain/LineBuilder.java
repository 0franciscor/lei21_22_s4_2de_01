package eapli.base.warehouse.domain;
public class LineBuilder {

    private Line line;

    private LineId Id;

    private Begin begin;

    private End end;

    private int numShelves;

    public LineBuilder(){}

    public LineBuilder withId(final LineId lineId){
        this.Id = Id;
        return this;
    }

    public LineBuilder withBegin(final Begin begin){
        this.begin = begin;
        return this;
    }

    public LineBuilder withEnd(final End end){
        this.end = end;
        return this;
    }

    public LineBuilder withNumShelves(final int numShelves){
        this.numShelves = numShelves;
        return this;
    }
    private Line buildLine(){
        if(line != null)
            return line;
        else if(Id != null && begin != null && end != null) {
            this.line = new Line(Id, begin, end, numShelves);
            return line;
        }
        return null;
    }

    public Line build(){
        final Line returnLine = buildLine();
        return returnLine;
    }
}
