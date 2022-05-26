package eapli.base.AGV.Domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AGVTask implements DomainEntity<Long>, AggregateRoot<Long> { //IMPLEMENTADO PARA EVITAR ERRO. REVISAO ACONSELHADA

    @Id
    private Long Id;
    private String description;

    public AGVTask(String id) {
        this.description = id;
    }
    public AGVTask() {}

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "AGVTask{" +
                "id='" + description + '\'' +
                '}';
    }

    @Override
    public boolean sameAs(Object other) {
        AGVTask obj = (AGVTask) other;
        return Id == obj.Id && description.equals(obj.description);
    }

    @Override
    public Long identity() {
        return Id;
    }
}
