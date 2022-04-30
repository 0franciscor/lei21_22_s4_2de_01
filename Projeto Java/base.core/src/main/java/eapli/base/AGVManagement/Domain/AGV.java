package eapli.base.AGVManagement.Domain;

import eapli.base.warehouse.domain.AGVDock;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;

@Entity
public class AGV implements AggregateRoot<AGVId> {

    @EmbeddedId
    private AGVId agvId;

    @OneToOne
    private AGVDock agvDock;

    @Embedded
    private Range range;

    @Embedded
    private MaxWeightCapacity maxWeightCapacity;

    @Embedded
    private MaxVolumeCapacity maxVolumeCapacity;

    @Embedded
    private Model model;

    @Embedded
    private BriefDescription briefDescription;

    @Embedded
    private AGVPosition position;

    private ChangeAGVStatus status;

    @Embedded
    private AGVTask agvTask;


    public AGV(){}

    public AGV(final AGVId agvId, final BriefDescription briefDescription, final Model model, final MaxWeightCapacity maxWeightCapacity, final MaxVolumeCapacity maxVolumeCapacity, final Range range, final AGVPosition position, final AGVDock agvDock, final ChangeAGVStatus status, final AGVTask agvTask){
        this.agvId = agvId;
        this.range = range;
        this.maxWeightCapacity = maxWeightCapacity;
        this.maxVolumeCapacity = maxVolumeCapacity;
        this.model = model;
        this.briefDescription = briefDescription;
        this.position = position;
        this.agvDock = agvDock;
        this.status = status;
        this.agvTask = agvTask;
    }



    @Override
    public boolean sameAs(Object other) {
        if(other == null) return false;
        if(this == other) return true;

        eapli.base.AGVManagement.Domain.AGV newObj = ((eapli.base.AGVManagement.Domain.AGV) other);

        return agvId == newObj.agvId && range == newObj.range && maxWeightCapacity == newObj.maxWeightCapacity && maxVolumeCapacity == newObj.maxVolumeCapacity
                && model == newObj.model && briefDescription == newObj.briefDescription && position == newObj.position && agvDock == newObj.agvDock && agvTask == newObj.agvTask;
    }

    public AGVDock agvDock() {
        return agvDock;
    }

    @Override
    public AGVId identity() {
        return agvId;
    }
}