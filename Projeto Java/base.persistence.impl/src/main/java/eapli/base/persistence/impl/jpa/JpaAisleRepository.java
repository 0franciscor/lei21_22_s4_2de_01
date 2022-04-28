package eapli.base.persistence.impl.jpa;

import eapli.base.warehouse.domain.Aisle;
import eapli.base.warehouse.repositories.AisleRepository;

public class JpaAisleRepository extends BasepaRepositoryBase<Aisle, Long, Long>
        implements AisleRepository {

    public JpaAisleRepository(){super("Id");}
}
