package eapli.base.persistence.impl.jpa;

import eapli.base.warehouse.domain.Line;
import eapli.base.warehouse.repositories.LineRepository;

public class JpaLineRepository extends BasepaRepositoryBase<Line, Long, Long> implements LineRepository {
    public JpaLineRepository() { super("Id");}
}
