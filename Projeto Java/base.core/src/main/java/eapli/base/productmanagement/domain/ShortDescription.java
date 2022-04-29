package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ShortDescription implements ValueObject, Serializable {

    private final String shortDescription;


    public ShortDescription(final String description) {
        Preconditions.nonEmpty(description);
        this.shortDescription = description;

    }

    protected ShortDescription() {
        this.shortDescription = "";
    }

    public static ShortDescription valueOf(final String description) {
        return new ShortDescription(description);
    }


    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof ShortDescription)) {
            return false;
        } else {
            ShortDescription shortDescription = (ShortDescription)o;
            return this.shortDescription.equals(shortDescription.shortDescription);
        }
    }

    public int hashCode() {
        HashCoder coder = (new HashCoder()).with(this.shortDescription);
        return coder.code();
    }

    public String toString() {
        return this.shortDescription;
    }
}
