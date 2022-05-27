package eapli.base.shoppingcarmanagement.repository;

import eapli.base.categorymanagement.domain.Category;
import eapli.base.productmanagement.domain.BrandName;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.TechnicalDescription;
import eapli.base.productmanagement.domain.UniqueInternalCode;
import eapli.base.shoppingcarmanagement.domain.ShoppingCar;
import eapli.framework.domain.repositories.DomainRepository;

public interface ShoppingCarRepository extends DomainRepository<Long, ShoppingCar> {

}
