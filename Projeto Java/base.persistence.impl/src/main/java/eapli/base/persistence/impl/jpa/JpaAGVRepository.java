package eapli.base.persistence.impl.jpa;

import eapli.base.AGVManagement.Domain.AGV;
import eapli.base.AGVManagement.Domain.AGVId;
import eapli.base.AGVManagement.Repositories.AGVRepository;

public class JpaAGVRepository extends BasepaRepositoryBase<AGV, AGVId, AGVId> implements AGVRepository {

    JpaAGVRepository() {
        super("id");
    }

}
