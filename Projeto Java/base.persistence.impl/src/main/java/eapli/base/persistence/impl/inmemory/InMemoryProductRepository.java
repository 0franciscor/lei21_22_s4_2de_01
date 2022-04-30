package eapli.base.persistence.impl.inmemory;

import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.UniqueInternalCode;
import eapli.base.productmanagement.repository.ProductRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

public class InMemoryProductRepository extends InMemoryDomainRepository<Product, Long> implements ProductRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Product findByUniqueInternalCode(UniqueInternalCode uniqueInternalCode) {
        Optional<Product> product = matchOne(e -> e.uniqueInternalCode().equals(uniqueInternalCode));
        Product product1 = product.get();
        return product1;
    }

}
