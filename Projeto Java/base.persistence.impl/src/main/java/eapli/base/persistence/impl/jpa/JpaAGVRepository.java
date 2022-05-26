package eapli.base.persistence.impl.jpa;

import eapli.base.AGV.Domain.AGV;
import eapli.base.AGV.Domain.AGVId;
import eapli.base.AGV.Domain.AGVStatus;
import eapli.base.AGV.Repositories.AGVRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaBaseRepository;

import javax.persistence.TypedQuery;
import java.util.Optional;

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
    public AGV getAGVById(String id) {
        final TypedQuery<AGV> query = entityManager().createQuery(
                "SELECT d FROM AGV d  WHERE d.agvId = :id",
                AGV.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }
}
