package eapli.base.AGV.Domain;

import eapli.base.ordermanagement.domain.ProductOrder;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class AGVTask implements DomainEntity<Long>, AggregateRoot<Long> { //IMPLEMENTADO PARA EVITAR ERRO. REVISAO ACONSELHADA

    @Id
    @GeneratedValue
    private Long Id;

    @Embedded
    private String description;

    @OneToMany
    private ProductOrder orders;


    public AGVTask(String id, ProductOrder orders) {

        this.description = id;
        this.orders = orders;
    }
    public AGVTask() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String agvTask) {
        this.description = agvTask;
    }

    public void addMoreOrders(ProductOrder order){
        orders = order;
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
