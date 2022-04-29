package eapli.base.persistence.impl.inmemory;

import eapli.base.warehouse.domain.Line;
import eapli.base.warehouse.repositories.LineRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryLineRepository extends InMemoryDomainRepository<Line, Long> implements LineRepository {

    static {
        InMemoryInitializer.init();
    }
}
