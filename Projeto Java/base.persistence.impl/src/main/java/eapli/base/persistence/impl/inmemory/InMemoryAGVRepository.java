package eapli.base.persistence.impl.inmemory;

import eapli.base.AGV.Domain.AGV;
import eapli.base.AGV.Domain.AGVId;
import eapli.base.AGV.Repositories.AGVRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryAGVRepository extends InMemoryDomainRepository<AGV, AGVId> implements AGVRepository {

    static {
        InMemoryInitializer.init();
    }

}
