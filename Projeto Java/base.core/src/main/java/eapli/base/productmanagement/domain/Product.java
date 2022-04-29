package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Money;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

public class Product implements AggregateRoot<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long productId;

    @Column(unique = true,nullable = false)
    private UniqueInternalCode uniqueInternalCode;

    private ShortDescription shortDescription;

    private TechnicalDescription technicalDescription;

    private ExtendedDescription extendedDescription;

    private Weight weight;

    private Reference reference;

    @Column(unique = true,nullable = false)
    private Barcode barcode;

    private BrandName brandName;

    @ElementCollection
    private Set<Photo> photos;

    private ProductionCode productionCode;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "no_taxes_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "no_taxes_currency"))
    })
    private Money priceWithoutTaxes;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "taxes_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "taxes_currency"))
    })
    private Money priceWithTaxes;

    public Product(final UniqueInternalCode uniqueInternalCode, final Barcode barcode,final BrandName brandName, final ExtendedDescription extendedDescription, final Reference reference, final ShortDescription shortDescription, final TechnicalDescription technicalDescription,final Weight weight){
        this.uniqueInternalCode=uniqueInternalCode;
        this.barcode = barcode;
        this.brandName = brandName;
        this.extendedDescription = extendedDescription;
        this.reference=reference;
        this.shortDescription=shortDescription;
        this.technicalDescription=technicalDescription;
        this.weight=weight;
    }

    protected Product() {
        //for ORM only
    }

    public void addProductionCode(final ProductionCode productionCode) {
        this.productionCode = productionCode;
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Long identity() {
<<<<<<< HEAD
        return this.productId;
=======
        return null;
>>>>>>> 934bf8ce4be801cd51cff203e5e08d85288bbd0e
    }
}
