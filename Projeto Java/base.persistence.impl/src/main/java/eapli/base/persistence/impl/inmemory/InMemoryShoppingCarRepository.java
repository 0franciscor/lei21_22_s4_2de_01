package eapli.base.persistence.impl.inmemory;

import eapli.base.shoppingcarmanagement.domain.ShoppingCar;
import eapli.base.shoppingcarmanagement.repository.ShoppingCarRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryShoppingCarRepository extends InMemoryDomainRepository<ShoppingCar, Long>
        implements ShoppingCarRepository {

    static {
        InMemoryInitializer.init();
    }
}
