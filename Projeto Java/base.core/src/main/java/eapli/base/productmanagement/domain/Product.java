package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.Id;
import java.io.Serializable;

public class Product implements AggregateRoot<UniqueInternalCode>, Serializable {

    @Id
    private UniqueInternalCode uniqueInternalCode;

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public UniqueInternalCode identity() {
        return null;
    }
}
