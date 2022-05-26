package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordermanagement.domain.ProductOrder;
import eapli.base.ordermanagement.repository.OrderRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaOrderRepository extends JpaAutoTxRepository<ProductOrder, Long, Long>
    implements OrderRepository {


    public JpaOrderRepository(String puname){
        super(puname, Application.settings().getExtendedPersistenceProperties(),"eapli.base");
    }

    @Override
    public ProductOrder getProductOrderById(Long id){
        final TypedQuery<ProductOrder> query = super.createQuery(
                "SELECT d FROM ProductOrder d WHERE orderid = '" + id + "'",
                ProductOrder.class);
        return query.getSingleResult();
    }



}
