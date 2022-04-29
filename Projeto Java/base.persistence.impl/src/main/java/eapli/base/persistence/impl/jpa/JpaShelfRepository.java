package eapli.base.persistence.impl.jpa;


import eapli.base.warehouse.domain.Shelf;
import eapli.base.warehouse.repositories.ShelfRepository;

public class JpaShelfRepository extends BasepaRepositoryBase<Shelf, Long, Long> implements ShelfRepository {

    public JpaShelfRepository() { super("Id");}
}
