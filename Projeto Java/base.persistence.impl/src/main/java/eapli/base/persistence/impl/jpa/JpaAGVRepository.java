package eapli.base.persistence.impl.jpa;

import eapli.base.AGV.domain.AGV;
import eapli.base.AGV.domain.AGVId;
import eapli.base.AGV.repositories.AGVRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaAGVRepository extends JpaAutoTxRepository<AGV, AGVId, AGVId> implements AGVRepository {

    public JpaAGVRepository(String persistenceName, String name) {
        super(persistenceName, name);
    }

    public JpaAGVRepository(TransactionalContext autoTx, String name){
        super(autoTx, name);
    }

    @Override
    public Iterable<AGV> getAGVsAvaiable() {

        final TypedQuery<AGV> query = entityManager().createQuery(
                "SELECT d FROM AGV d  WHERE d.agvStatus = :status",
                AGV.class);
        query.setParameter("status", "Is Free");

        return query.getResultList();
    }

    /*@Override
    public AGVStatus changeStatusOfAGV(AGV agv) {
        final TypedQuery<AGV> query = entityManager().createQuery(
                "SELECT d FROM AGV d  WHERE d.agvId = :id",
                AGVStatus.class);
        query.setParameter("id", id);

    }*/

    @Override
    public AGV getAGVById(AGVId agvId) {
        final TypedQuery<AGV> query = super.createQuery(
                "SELECT d FROM AGV d  WHERE agvId = '" + agvId + "'",
                AGV.class);
        return query.getSingleResult();
    }
}
