package eapli.base.AGVManagement.Domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Model implements ValueObject {
    private String model;

    public Model(String model) {
        this.model = model;
    }

    public Model(){}

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    @Override
    public String toString() {
        return "Model{" +
                "model='" + model + '\'' +
                '}';
    }
}