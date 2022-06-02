package eapli.base.persistence.impl.jpa;

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


    public JpaOrderRepository(TransactionalContext autoTx, String name) {
        super(autoTx, name);
    }

    @Override
    public ProductOrder getOrderById (Long id){
        final TypedQuery<ProductOrder> query = super.createQuery(
                "SELECT d FROM ProductOrder d WHERE orderid = '" + id + "'",
                ProductOrder.class);
        return query.getSingleResult();

    }


    @Override
    public Iterable<ProductOrder> getOrdersWhoNeedToBePrepared () {
        final TypedQuery<ProductOrder> query = entityManager().createQuery(
                "SELECT d FROM ProductOrder d  WHERE d.status = :status",
                ProductOrder.class);
        query.setParameter("status", new OrderStatus(OrderStatus.Status.TO_BE_PREPARED));

        return query.getResultList();
    }

    @Override
    public Iterable<ProductOrder> findOrdersDispatchedForCustomerDelivery() {
        final TypedQuery<ProductOrder> query = entityManager().createQuery(
                "SELECT d FROM ProductOrder d  WHERE d.status = :status",
                ProductOrder.class);
        query.setParameter("status", new OrderStatus(OrderStatus.Status.DISPATCHED_FOR_COSTUMER));

        return query.getResultList();
    }

    @Override
    public Iterable<ProductOrder> getOpenOrdersOfAClient(Long clientId) {
        final TypedQuery<ProductOrder> query = entityManager().createQuery(
                "SELECT d1 FROM ProductOrder d1 WHERE d1.id NOT IN (SELECT d.id FROM ProductOrder d JOIN d.client aind WHERE aind.id = :id AND d.status = :status)",
                ProductOrder.class);
        query.setParameter("id", clientId);
        query.setParameter("status", OrderStatus.Status.RECEIVED_BY_COSTUMER);

        return query.getResultList();
    }
}
