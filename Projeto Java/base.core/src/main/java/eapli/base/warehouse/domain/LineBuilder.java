package eapli.base.warehouse.domain;

import java.util.List;

public class LineBuilder {

    private Line line;

    private Long Id;

    private List<Shelf> shelfList;

    private Begin begin;

    private End end;

    private int numShelves;

    public LineBuilder(Long Id, List<Shelf> shelfList, Begin begin, End end, int numShelves){
        this.Id = Id;
        this.shelfList = shelfList;
        this.begin = begin;
        this.end = end;
        this.numShelves = numShelves;

    }
    public LineBuilder(Long Id, Begin begin, End end, int numShelves){
        this.Id = Id;
        this.begin = begin;
        this.end = end;
        this.numShelves = numShelves;
    }

    private Line buildLine(){
        if(line != null)
            return line;
        else if(begin != null && end != null) {
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
