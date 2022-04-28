package eapli.base.ordermanagement.domain;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.productmanagement.domain.UniqueInternalCode;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Money;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.time.util.Calendars;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class ProductOrder implements AggregateRoot<Long>, Serializable {
    private static final long serialVersionUID = 1L;

    public enum SourceChannel {
        CALL, EMAIL, MEETING;
    }

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long orderId;

    @ManyToOne
    private Client client;

    @Temporal(TemporalType.DATE)
    private Calendar createdOn;

    private OrderStatus status;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "streetName", column = @Column(name = "billing_streetName")),
            @AttributeOverride(name = "doorNumber", column = @Column(name = "billing_doorNumber")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "billing_postalCode")),
            @AttributeOverride(name = "city", column = @Column(name = "billing_city")),
            @AttributeOverride(name = "country", column = @Column(name = "billing_country"))
    })
    private Address billingAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "streetName", column = @Column(name = "shipping_streetName")),
            @AttributeOverride(name = "doorNumber", column = @Column(name = "shipping_doorNumber")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "shipping_postalCode")),
            @AttributeOverride(name = "city", column = @Column(name = "shipping_city")),
            @AttributeOverride(name = "country", column = @Column(name = "shipping_country"))
    })
    private Address shippingAddress;

    @OneToMany
    Set<OrderItem> items = new HashSet<>();


    /**
     * Map where:
     * > Key: Unique Internal Code of Product
     * > Value: an Order Item entity
     *
     * Might be useful for future US's, namely related to the Shopping Cart
     */
    @OneToMany
    @JoinTable(name = "map_items",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id")})
    @MapKeyJoinColumn(name = "product_code")
    private Map<UniqueInternalCode, OrderItem> mapItems = new HashMap<>();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "no_taxes_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "no_taxes_currency"))
    })
    private Money totalAmountWithoutTaxes;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "taxes_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "taxes_currency"))
    })
    private Money totalAmountWithTaxes;

    @Enumerated(EnumType.STRING)
    private Shipment shipment;

    private Payment payment;

    private OrderVolume orderVolume;

    private OrderWeight orderWeight;

    @Enumerated(EnumType.STRING)
    private SourceChannel sourceChannel;

    @Temporal(TemporalType.DATE)
    private Calendar interactionDate;

    private AdditionalComment additionalComment;

    @ManyToOne
    private SystemUser salesClerk;

    public ProductOrder(final Client client, final Address billingAddress, final Address shippingAddress, final Shipment shipment, final SourceChannel sourceChannel, final Calendar interactionDate, final AdditionalComment additionalComment, final SystemUser salesClerk) {
        this.createdOn = Calendars.now();
        this.client = client;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        //this.items = items;
        this.shipment = shipment;
        //this.payment = payment;
        this.sourceChannel = sourceChannel;
        this.interactionDate = interactionDate;
        this.additionalComment = additionalComment;
        this.salesClerk = salesClerk;
    }

    public ProductOrder(final Client client, final Address billingAddress, final Address shippingAddress, final Shipment shipment, final SourceChannel sourceChannel, final Calendar interactionDate, final SystemUser salesClerk) {
        this.createdOn = Calendars.now();
        this.client = client;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        //this.items = items;
        this.shipment = shipment;
        //this.payment = payment;
        this.sourceChannel = sourceChannel;
        this.interactionDate = interactionDate;
        this.salesClerk = salesClerk;
    }

    protected ProductOrder() {
        //for ORM purposes
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Long identity() {
        return this.orderId;
    }




}
