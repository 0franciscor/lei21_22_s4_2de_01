package eapli.base.ordermanagement.repository;

import eapli.base.AGV.Domain.AGV;
import eapli.base.ordermanagement.domain.ProductOrder;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.repositories.DomainRepository;

public interface OrderRepository extends DomainRepository<Long, ProductOrder> {


    Iterable<ProductOrder> getOrdersWhoNeedToBePrepared();

    public ProductOrder getProductOrderById(Long id);

}
