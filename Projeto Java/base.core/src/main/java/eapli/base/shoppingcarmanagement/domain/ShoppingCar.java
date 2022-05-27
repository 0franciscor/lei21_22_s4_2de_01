package eapli.base.shoppingcarmanagement.domain;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.ordermanagement.domain.OrderItem;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class ShoppingCar implements AggregateRoot<Long>, Serializable {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long shoppingCarId;

    @OneToOne
    private Client client;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "shopping_car_id")
    private List<OrderItem> items;

    public ShoppingCar(final Client client, final List<OrderItem> items) {
        this.client = client;
        this.items = items;
    }

    protected ShoppingCar() {
        //for ORM purposes
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Long identity() {
        return this.shoppingCarId;
    }
}
