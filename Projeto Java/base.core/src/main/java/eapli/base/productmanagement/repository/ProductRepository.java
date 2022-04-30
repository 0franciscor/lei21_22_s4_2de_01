package eapli.base.productmanagement.repository;

import eapli.base.productmanagement.domain.Product;

import eapli.base.productmanagement.domain.UniqueInternalCode;
import eapli.framework.domain.repositories.DomainRepository;

public interface ProductRepository extends DomainRepository<Long, Product> {

    Product findByUniqueInternalCode(UniqueInternalCode uniqueInternalCode);

}