package eapli.base.AGVManagement.Domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;

@Entity
public class AGV implements AggregateRoot<AGVId> {

    @EmbeddedId
    private AGVId id;

    @Embedded
    private Range range;

    @Embedded
    private MaxWeightCapacity maxWeightCapacity;

    @Embedded
    private Model model;

    @Embedded
    private BriefDescription briefDescription;

    @Embedded
    private AGVPosition position;


    public AGV(){}

    public AGV(final AGVId id, final BriefDescription briefDescription, final Model model, final MaxWeightCapacity maxWeightCapacity, final Range range, final AGVPosition position){
        this.id = id;
        this.range = range;
        this.maxWeightCapacity = maxWeightCapacity;
        this.model = model;
        this.briefDescription = briefDescription;
        this.position = position;
    }

    @Override
    public boolean sameAs(Object other) {
        if(other == null) return false;
        if(this == other) return true;

        eapli.base.AGVManagement.Domain.AGV newObj = ((eapli.base.AGVManagement.Domain.AGV) other);

        return id == newObj.id && range == newObj.range && maxWeightCapacity == newObj.maxWeightCapacity && model == newObj.model && briefDescription == newObj.briefDescription && position == newObj.position;
    }

    @Override
    public AGVId identity() {
        return id;
    }
}