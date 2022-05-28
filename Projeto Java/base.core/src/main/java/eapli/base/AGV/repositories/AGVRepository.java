package eapli.base.AGV.repositories;

import eapli.base.AGV.domain.AGV;
import eapli.base.AGV.domain.AGVId;
import eapli.framework.domain.repositories.DomainRepository;

public interface AGVRepository extends DomainRepository<AGVId, AGV> {

    public Iterable<AGV> getAGVsAvaiable();

    public AGV getAGVById(AGVId agvId);

    public AGV getAGVByIdSt(String agvId);

}
