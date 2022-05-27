package eapli.base.persistence.impl.jpa;

import eapli.base.shoppingcarmanagement.domain.ShoppingCar;
import eapli.base.shoppingcarmanagement.repository.ShoppingCarRepository;

public class JpaShoppingCarRepository extends BasepaRepositoryBase<ShoppingCar, Long, Long>
        implements ShoppingCarRepository {

    JpaShoppingCarRepository() {
        super("shoppingCarId");
    }
}
