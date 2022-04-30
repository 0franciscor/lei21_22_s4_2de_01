package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordermanagement.domain.OrderItem;
import eapli.base.ordermanagement.repository.OrderItemRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaOrderItemRepository extends JpaAutoTxRepository<OrderItem, Long, Long>
        implements OrderItemRepository {


    public JpaOrderItemRepository(String puname){
        super(puname, Application.settings().getExtendedPersistenceProperties(),"eapli.base");
    }

}
