package eapli.base.warehouse.domain;

public class AGVDockBuilder {

    private AGVDock agvDock;
    private String Id;

    private Depth depth;

    private Accessibility accessibility;

    private Begin begin;

    private End end;

    public AGVDockBuilder(String Id, Depth depth, Accessibility accessibility, Begin begin, End end){
        this.Id = Id;
        this.depth = depth;
        this.accessibility = accessibility;
        this.begin = begin;
        this.end = end;
    }

    private AGVDock buildDock(){
        if(agvDock != null)
            return agvDock;
        else if(depth != null && accessibility != null && begin != null && end != null) {
            this.agvDock = new AGVDock(Id, depth, accessibility, begin, end);
            return agvDock;
        }
        return null;
    }

    public AGVDock build(){
        final AGVDock returnDock = buildDock();
        return returnDock;
    }
}
