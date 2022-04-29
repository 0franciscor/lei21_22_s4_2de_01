package eapli.base.AGVManagement.Domain;

import eapli.base.clientmanagement.domain.Client;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainFactory;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

public class AGVBuilder implements DomainFactory<AGV> {

    private AGV agv;

    private AGVId id;

    private Range range;

    private MaxWeightCapacity maxWeightCapacity;

    private Model model;

    private BriefDescription briefDescription;

    private AGVPosition position;

    public AGVBuilder withId(final AGVId id) {
        this.id = id;
        return this;
    }

    public AGVBuilder withRange(final Range range) {
        this.range = range;
        return this;
    }

    public AGVBuilder withMaxWeightCapacity(final MaxWeightCapacity maxWeightCapacity) {
        this.maxWeightCapacity = maxWeightCapacity;
        return this;
    }

    public AGVBuilder withModel(final Model model) {
        this.model = model;
        return this;
    }

    public AGVBuilder withBriefDescription(final BriefDescription briefDescription) {
        this.briefDescription = briefDescription;
        return this;
    }

    public AGVBuilder withPosition(final AGVPosition position){
        this.position = position;
        return this;
    }

    @Override
    public AGV build() {
        final AGV ret = buildOrThrow();
        agv = null;
        return  ret;
    }

    private AGV buildOrThrow() {
        if (agv != null) {
            return agv;
        } else if (id != null && range != null && maxWeightCapacity != null && model != null && briefDescription != null) {
            agv = new AGV(id,briefDescription, model, maxWeightCapacity, range, position);
            return agv;
        } else {
            throw new IllegalStateException();
        }
    }
}