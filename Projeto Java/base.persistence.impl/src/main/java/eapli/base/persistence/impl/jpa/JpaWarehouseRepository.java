package eapli.base.persistence.impl.jpa;

import eapli.base.warehouse.domain.Warehouse;
import eapli.base.warehouse.repositories.WarehouseRepository;

public class JpaWarehouseRepository extends BasepaRepositoryBase<Warehouse, Long, Long> implements WarehouseRepository {

    public JpaWarehouseRepository() {
        super("Id");
    }
}
