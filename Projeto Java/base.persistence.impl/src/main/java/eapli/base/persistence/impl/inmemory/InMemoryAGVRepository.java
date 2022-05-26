package eapli.base.persistence.impl.inmemory;

import eapli.base.AGV.Domain.AGV;
import eapli.base.AGV.Domain.AGVId;
import eapli.base.AGV.Domain.AGVStatus;
import eapli.base.AGV.Repositories.AGVRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

public class InMemoryAGVRepository extends InMemoryDomainRepository<AGV, AGVId> implements AGVRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<AGV> getAGVsAvaiable() {
        return match(e->e.getAgvStatus().equals(AGVStatus.FREE));
    }

    /*@Override
    public AGVStatus changeStatusOfAGV(AGV agv) {
        return agv.getAgvStatus();
    }*/

    @Override
    public AGV getAGVById(String id) {
        return matchOne(e->e.getAgvId().getAGVId().equals(id)).get();
    }
}
