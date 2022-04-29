package eapli.base.persistence.impl.jpa;

import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repository.ProductRepository;

public class JpaProductRepository extends BasepaRepositoryBase<Product, Long, Long>
    implements ProductRepository {

    JpaProductRepository() {
        super("productId");
    }


}
