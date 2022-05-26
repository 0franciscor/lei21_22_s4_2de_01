package eapli.base.persistence.impl.jpa;

import eapli.base.AGV.Domain.AGV;
import eapli.base.Application;
import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.ProductOrder;
import eapli.base.ordermanagement.repository.OrderRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaOrderRepository extends JpaAutoTxRepository<ProductOrder, Long, Long>
    implements OrderRepository {


    public JpaOrderRepository(String puname){
        super(puname, Application.settings().getExtendedPersistenceProperties(),"eapli.base");
    }

    public JpaOrderRepository(TransactionalContext autoTx, String name){
        super(autoTx, name);
    }


    @Override
    public Iterable<ProductOrder> getOrdersWhoNeedToBePrepared() {
        final TypedQuery<ProductOrder> query = entityManager().createQuery(
                "SELECT d FROM ProductOrder d  WHERE d.status = :status",
                ProductOrder.class);
        query.setParameter("status", OrderStatus.Status.TO_BE_PREPARED);

        return query.getResultList();
    }
}
