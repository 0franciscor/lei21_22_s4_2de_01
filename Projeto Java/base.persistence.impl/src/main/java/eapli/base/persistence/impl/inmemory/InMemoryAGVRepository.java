package eapli.base.persistence.impl.inmemory;

import eapli.base.AGVManagement.Domain.AGV;
import eapli.base.AGVManagement.Domain.AGVId;
import eapli.base.AGVManagement.Repositories.AGVRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryAGVRepository extends InMemoryDomainRepository<AGV, AGVId> implements AGVRepository {

    static {
        InMemoryInitializer.init();
    }

}
