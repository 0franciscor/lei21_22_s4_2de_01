package eapli.base.persistence.impl.jpa;

import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.UniqueInternalCode;
import eapli.base.productmanagement.repository.ProductRepository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Optional;

public class JpaProductRepository extends BasepaRepositoryBase<Product, Long, Long>
    implements ProductRepository {

    JpaProductRepository() {
        super("productId");
    }



    @Override
    public Product findByUniqueInternalCode(UniqueInternalCode uniqueInternalCode) {
        try {
        Query query = super.entityManager().createQuery("SELECT e FROM Product e WHERE e.uniqueInternalCode=:uniqueInternalCode");
        query.setParameter("uniqueInternalCode", uniqueInternalCode);

            Product product = (Product) query.getSingleResult();
            return product;
        } catch (NoResultException e){
            return null;
        }

    }


}
