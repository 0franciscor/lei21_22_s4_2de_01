package eapli.base.ordermanagement.domain;

import eapli.base.productmanagement.domain.BrandName;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class OrderItem implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    private int quantity;


    private String codeItem;

    public OrderItem(final int qty, final String codeItem) {
        Preconditions.nonEmpty(codeItem, "Code should neither be null nor empty");
        Preconditions.nonNull(qty, "Quantity should not be null");
        Preconditions.isPositive(qty, "The quantity of the wished Product must be positive.");

        quantity = qty;
        this.codeItem = codeItem;
    }

    protected OrderItem() {
        //for ORM purposes
    }

    public String product() {
        return codeItem;
    }



    public static OrderItem valueOf(final String code, final Integer quantity) {
        return new OrderItem( quantity,code);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof OrderItem)) {
            return false;
        } else {
            OrderItem orderItem = (OrderItem) o;
            return this.codeItem.equals(orderItem.codeItem);
        }
    }


    public String code() {
        return this.codeItem;
    }

    public Integer quantity() {
        return this.quantity;
    }

    @Override
    public int hashCode() {
        return (new HashCoder()).with(this.codeItem).with(this.quantity).code();
    }

    @Override
    public String toString() {
        return "Produce Code: " + this.codeItem + "| Quantity: " + this.quantity;
    }

}
