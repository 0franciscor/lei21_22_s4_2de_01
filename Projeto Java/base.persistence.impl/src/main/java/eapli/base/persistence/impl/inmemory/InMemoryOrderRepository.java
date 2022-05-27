package eapli.base.persistence.impl.inmemory;

import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.ProductOrder;
import eapli.base.ordermanagement.repository.OrderRepository;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryOrderRepository extends InMemoryDomainRepository<ProductOrder, Long> implements OrderRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<ProductOrder> getOrdersWhoNeedToBePrepared() {
        return match(e->e.getOrderStatus().equals(OrderStatus.Status.TO_BE_PREPARED));
    }



    @Override
    public ProductOrder getOrderById(Long id){
        return null;
    }
}
