package eapli.base.productmanagement.dto;


import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * DTO for products.
 *
 * @author Rita Ariana Sobral
 *
 */
public class ProductDTO {

    private final String productUniqueInternalCode;

    private final String productShortDescription;

    private final BigDecimal price;

    public ProductDTO(final String productUniqueInternalCode,final String productShortDescription,final BigDecimal price){
        this.price=price;
        this.productShortDescription=productShortDescription;
        this.productUniqueInternalCode=productUniqueInternalCode;
    }
}
