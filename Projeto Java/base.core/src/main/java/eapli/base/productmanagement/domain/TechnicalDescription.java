package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TechnicalDescription implements ValueObject, Serializable {

    private final String description;


    public TechnicalDescription(final String description) {
        Preconditions.nonEmpty(description);
        this.description = description;

    }

    protected TechnicalDescription() {
        this.description = "";
    }

    public static TechnicalDescription valueOf(final String description) {
        return new TechnicalDescription(description);
    }


    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof TechnicalDescription)) {
            return false;
        } else {
            TechnicalDescription shortDescription = (TechnicalDescription)o;
            return this.description.equals(shortDescription.description);
        }
    }

    public int hashCode() {
        HashCoder coder = (new HashCoder()).with(this.description);
        return coder.code();
    }

    public String toString() {
        return this.description;
    }
}
