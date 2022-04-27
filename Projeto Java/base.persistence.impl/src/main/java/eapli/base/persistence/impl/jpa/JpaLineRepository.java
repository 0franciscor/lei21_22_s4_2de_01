package eapli.base.persistence.impl.jpa;

import eapli.base.warehouse.domain.Line;

public class JpaLineRepository extends BasepaRepositoryBase<Line, Long, Long>{
    public JpaLineRepository() { super("Id");}
}
