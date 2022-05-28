package eapli.base.AGV.domain;

import eapli.base.ordermanagement.domain.ProductOrder;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntity;

import javax.persistence.*;
import java.util.ArrayList;
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
    @JoinColumn(name = "Orders")
    private List<ProductOrder> orders;

    private Long totalWeight;

    private Long totalVolume;


    public AGVTask(String description) {

        this.description = description;
        this.totalWeight = 0L;
        this.totalVolume = 0L;
        this.orders = new ArrayList<>();
    }

    protected AGVTask() {
        this.totalWeight = 0L;
        this.totalVolume = 0L;
        this.orders = new ArrayList<>();
    }

    public String getDescription() {
        return description;
    }

    public void addMoreOrders(ProductOrder order){
        this.orders.add(order);
        totalWeight += order.getOrderWeight().getWeight();
        totalVolume += order.getOrderVolume().getVolume();
    }

    public Long getTotalWeight() {
        return totalWeight;
    }

    public Long getTotalVolume() {
        return totalVolume;
    }

    public void incrementVolume(Long volume){
        this.totalVolume += volume;
    }

    public void incrementWeight(Long weight){
        this.totalWeight += weight;
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
