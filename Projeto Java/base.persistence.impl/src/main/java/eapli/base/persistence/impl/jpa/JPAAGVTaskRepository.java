package eapli.base.persistence.impl.jpa;

import eapli.base.AGV.Domain.AGVTask;
import eapli.base.AGV.Repositories.AGVTaskRepository;

public class JPAAGVTaskRepository extends BasepaRepositoryBase<AGVTask, Long, Long>
        implements AGVTaskRepository {

    public JPAAGVTaskRepository() {
        super("Id");
    }
}
