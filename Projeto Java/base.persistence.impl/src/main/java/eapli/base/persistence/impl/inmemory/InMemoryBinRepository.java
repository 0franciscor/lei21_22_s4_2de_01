package eapli.base.persistence.impl.inmemory;

import eapli.base.warehouse.domain.Bin;
import eapli.base.warehouse.repositories.BinRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryBinRepository extends InMemoryDomainRepository<Bin, Long> implements BinRepository {

    static {
        InMemoryInitializer.init();
    }
}
