package eapli.base.persistence.impl.jpa;

import eapli.base.warehouse.domain.AGVDock;
import eapli.base.warehouse.repositories.AGVDockRepository;

public class JpaAGVDockRepository extends BasepaRepositoryBase<AGVDock, String, String> implements AGVDockRepository {

    JpaAGVDockRepository(){super("Id");}
}
