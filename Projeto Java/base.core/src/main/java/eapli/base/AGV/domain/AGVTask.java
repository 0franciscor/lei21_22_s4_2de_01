package eapli.base.AGV.domain;

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

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "productId")
    private List<ProductOrder> orders;

    private int totalWeight;

    private int totalVolume;


    public AGVTask(String description) {

        this.description = description;
        this.totalWeight = 0;
        this.totalVolume = 0;
    }

    public AGVTask() {
        this.totalWeight = 0;
        this.totalVolume = 0;
    }

    public String getDescription() {
        return description;
    }

    public void addMoreOrders(ProductOrder order){
        this.orders.add(order);
        totalWeight += order.getOrderWeight().getWeight();
        totalVolume += order.getOrderVolume().getVolume();
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public int getTotalVolume() {
        return totalVolume;
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
