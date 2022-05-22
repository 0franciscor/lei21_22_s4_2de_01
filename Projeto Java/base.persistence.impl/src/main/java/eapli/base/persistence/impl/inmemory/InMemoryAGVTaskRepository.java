package eapli.base.persistence.impl.inmemory;

import eapli.base.AGV.Domain.AGVTask;
import eapli.base.AGV.Repositories.AGVTaskRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;


public class InMemoryAGVTaskRepository extends InMemoryDomainRepository<AGVTask, Long> implements AGVTaskRepository {

    static {
        InMemoryInitializer.init();
    }
}
