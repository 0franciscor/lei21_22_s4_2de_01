package eapli.base.persistence.impl.jpa;

import eapli.base.ordermanagement.domain.ProductOrder;
import eapli.base.ordermanagement.repository.OrderRepository;

public class JpaOrderRepository extends BasepaRepositoryBase<ProductOrder, Long, Long>
    implements OrderRepository {

    JpaOrderRepository() {
        super("orderId");
    }


}
