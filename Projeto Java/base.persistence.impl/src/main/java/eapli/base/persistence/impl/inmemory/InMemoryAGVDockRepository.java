package eapli.base.persistence.impl.inmemory;


import eapli.base.warehouse.domain.AGVDock;
import eapli.base.warehouse.repositories.AGVDockRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryAGVDockRepository extends InMemoryDomainRepository<AGVDock, String> implements AGVDockRepository {

    static {
        InMemoryInitializer.init();
    }
}
