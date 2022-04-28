package eapli.base.persistence.impl.jpa;

import eapli.base.warehouse.domain.WarehousePlant;
import eapli.base.warehouse.repositories.WarehousePlantRepository;

public class JpaWarehousePlantRepository extends BasepaRepositoryBase<WarehousePlant, Long, Long>
        implements WarehousePlantRepository {

    public JpaWarehousePlantRepository() {
        super("Id");
    }
}