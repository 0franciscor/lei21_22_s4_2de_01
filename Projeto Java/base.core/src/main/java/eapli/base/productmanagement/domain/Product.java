package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.Version;
import java.io.Serializable;


public class Product implements AggregateRoot<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    private BrandName brandName;

    /**
     * Constructor for Product with the minimum attributes.
     *
     *
     */
    public Product(final BrandName brandName) {
        Preconditions.noneNull(brandName);

        this.brandName = brandName;

    }

    protected Product() {
        //for ORM only
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Long identity() {
        return null;
    }
}
