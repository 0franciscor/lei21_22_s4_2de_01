package eapli.base.clientmanagement.domain;

import eapli.framework.domain.model.DomainFactory;

import java.util.Calendar;
import java.util.Set;

public class ClientBuilder implements DomainFactory<Client> {

    private Client theClient;

    private Name name;

    private VAT vat;

    private Email email;

    private PhoneNumber phoneNumber;

    private Set<PostalAddress> postalAddresses;

    public ClientBuilder named(final Name name) {
        this.name = name;
        return this;
    }

    public ClientBuilder withVAT(final VAT vat) {
        this.vat = vat;
        return this;
    }

    public ClientBuilder withEmail(final Email email) {
        this.email = email;
        return this;
    }

    public ClientBuilder withPhoneNumber(final PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ClientBuilder withAddresses(final Set<PostalAddress> postalAddresses) {
        if (postalAddresses != null) {
            postalAddresses.forEach(this::withAddress);
        }
        return this;
    }

    public ClientBuilder withAddress(final PostalAddress postalAddress) {
        buildOrThrow();
        this.postalAddresses.add(postalAddress);
        return this;
    }

    private Client buildOrThrow() {
        if (theClient != null) {
            return theClient;
        } else if (name != null && vat != null && email != null && phoneNumber != null && !postalAddresses.isEmpty()) {
            theClient = new Client(name, vat, email, phoneNumber, postalAddresses);
            return theClient;
        } else {
            throw new IllegalStateException();
        }
    }

    public ClientBuilder withGender(final Client.Gender gender) {
        buildOrThrow();
        theClient.addGender(gender);
        return this;
    }

    public ClientBuilder withBirthdate(final Calendar birthdate) {
        buildOrThrow();
        theClient.insertBirthDate(birthdate);
        return this;
    }

    @Override
    public Client build() {
        final Client ret = buildOrThrow();
        theClient = null;
        return ret;
    }





}
