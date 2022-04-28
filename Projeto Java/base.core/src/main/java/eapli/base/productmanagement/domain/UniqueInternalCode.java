package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import java.io.Serializable;

public class UniqueInternalCode implements ValueObject, Serializable, Comparable<UniqueInternalCode> {


    @Override
    public int compareTo(UniqueInternalCode o) {
        return 0;
    }
}
