package eapli.base.persistence.impl.jpa;

import eapli.base.ordermanagement.domain.OrderItem;
import eapli.base.ordermanagement.domain.ProductOrder;
import eapli.base.ordermanagement.repository.OrderItemRepository;
import eapli.base.ordermanagement.repository.OrderRepository;

public class JpaOrderItemRepository extends BasepaRepositoryBase<OrderItem, Long, Long>
    implements OrderItemRepository {

    JpaOrderItemRepository() {
        super("orderItemId");
    }


}
