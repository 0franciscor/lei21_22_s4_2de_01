package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ExtendedDescription implements ValueObject, Serializable {

    private final String description;


    public ExtendedDescription(final String description) {
        Preconditions.nonEmpty(description);
        this.description = description;

    }

    protected ExtendedDescription() {
        this.description = "";
    }

    public static ExtendedDescription valueOf(final String description) {
        return new ExtendedDescription(description);
    }


    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof ExtendedDescription)) {
            return false;
        } else {
            ExtendedDescription shortDescription = (ExtendedDescription)o;
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
