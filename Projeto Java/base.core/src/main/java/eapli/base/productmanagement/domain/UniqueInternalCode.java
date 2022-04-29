package eapli.base.productmanagement.domain;

import eapli.base.clientmanagement.domain.VAT;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import java.io.Serializable;

public class UniqueInternalCode implements ValueObject, Serializable {

    private final String uniqueInternalCode;

    public UniqueInternalCode(String uniqueInternalCode){
        Preconditions.nonEmpty(uniqueInternalCode, "Unique Internal Code should neither be null nor empty");
        Preconditions.noneNull(uniqueInternalCode,"Unique Internal Code should neither be null nor empty");
        Preconditions.ensure(uniqueInternalCode.length()<23);
        this.uniqueInternalCode = uniqueInternalCode;
    }

    protected UniqueInternalCode() {
        this.uniqueInternalCode = "";
        //for ORM purposes
    }

    public static UniqueInternalCode valueOf(final String uniqueInternalCode) {
        return new UniqueInternalCode(uniqueInternalCode);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof UniqueInternalCode)) {
            return false;
        } else {
            UniqueInternalCode that = (UniqueInternalCode) o;
            return this.uniqueInternalCode.equals(that.uniqueInternalCode);
        }
    }

    @Override
    public int hashCode() {
        return (new HashCoder()).with(this.uniqueInternalCode).code();
    }

    @Override
    public String toString() {
        return this.uniqueInternalCode;
    }

}
