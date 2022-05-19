package eapli.base.persistence.impl.jpa;

import eapli.base.AGV.Domain.AGV;
import eapli.base.AGV.Domain.AGVId;
import eapli.base.AGV.Repositories.AGVRepository;

public class JpaAGVRepository extends BasepaRepositoryBase<AGV, AGVId, AGVId> implements AGVRepository {

    JpaAGVRepository() {
        super("id");
    }

}
