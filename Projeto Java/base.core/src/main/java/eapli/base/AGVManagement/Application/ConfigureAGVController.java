package eapli.base.AGVManagement.Application;

import eapli.base.AGVManagement.Domain.*;
import eapli.base.AGVManagement.Repositories.AGVRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehouse.domain.AGVDock;
import eapli.base.warehouse.repositories.AGVDockRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

/**
 * Created by Pedro Rocha
 */
public class ConfigureAGVController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AGVRepository agvRepository = PersistenceContext.repositories().agv();
    private final AGVDockRepository agvDockRepository = PersistenceContext.repositories().dock();

    public Iterable<AGVDock> configureAGVDock() {
        return agvDockRepository.findAll();
    }


    public boolean validateData(String agvId, String briefDescription, String model, String maxWeightCapacity, String maxVolumeCapacity, String range, String position) {
        if(agvId == null || briefDescription == null || model == null || maxWeightCapacity == null || maxVolumeCapacity == null || range == null || position == null ||
                agvId.length()!=8 || briefDescription.length()>30 || model.length()>50 || Double.parseDouble(maxWeightCapacity)<=0 || Double.parseDouble(maxVolumeCapacity)<=0 ||
                Double.parseDouble(range)<=0) return false;
        return true;
    }

    public AGV configureAGV(final String agvId, final String briefDescription, final String model, final Double maxWeightCapacity, final Double maxVolumeCapacity,
                            final Double range, final String position , final AGVDock agvDock, final String status) {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.WAREHOUSE_EMPLOYEE);

        final AGVBuilder newAGV = new AGVBuilder()
                .withId(new AGVId(agvId))
                .withBriefDescription(new BriefDescription(briefDescription))
                .withModel(new Model(model))
                .withMaxWeightCapacity(new MaxWeightCapacity(maxWeightCapacity))
                .withMaxVolumeCapacity(new MaxVolumeCapacity(maxVolumeCapacity))
                .withRange(new Range(range))
                .withPosition(new AGVPosition(position))
                .withAGVDock(agvDock).withAGVStatus(status);


        AGV agv = newAGV.build();


        return agvRepository.save(agv);
    }

}
