package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Embeddable
public class Payment implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    public enum Type {
        PAYPAL, APPLE_PAY;
    }

    @Enumerated(EnumType.STRING)
    private final Type paymentType;

    public Payment(final Type type) {
        this.paymentType = type;

    }

    protected Payment() {
        this.paymentType = null;
        //for ORM purposes
    }
}
