package eapli.base.AGVManagement.Domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class MaxVolumeCapacity implements ValueObject {
    private Double volume;

    public MaxVolumeCapacity(final double volume)    {
        this.volume = volume;
    }

    public MaxVolumeCapacity() {}


    public Double getMaxVolumeCapacity() {
        return volume;
    }

    public void setMaxVolumeCapacity(Double maxVolumeCapacity) {
        this.volume = maxVolumeCapacity;
    }

    @Override
    public String toString() {
        return "MaxVolumeCapacity{" +
                "volume=" + volume +
                '}';
    }
}
