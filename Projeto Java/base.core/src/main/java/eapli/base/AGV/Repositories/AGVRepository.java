package eapli.base.AGV.Repositories;

import eapli.base.AGV.Domain.AGV;
import eapli.base.AGV.Domain.AGVId;
import eapli.base.AGV.Domain.AGVStatus;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface AGVRepository extends DomainRepository<AGVId, AGV> {

    public Iterable<AGV> getAGVsAvaiable();

    /*public void changeStatusOfAGV(AGV agv);*/

    public AGV getAGVById(String id);

}
