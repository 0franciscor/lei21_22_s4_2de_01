package eapli.base.AGVManagement.Application;

import eapli.base.AGVManagement.Domain.*;
import eapli.base.AGVManagement.Repositories.AGVRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehouse.repositories.AGVDockRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

/**
 * Created by Pedro Rocha
 */
public class ConfigureAGVController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AGVRepository agvRepository = PersistenceContext.repositories().agv();
    //private final AGVDockRepository agvDockRepository = PersistenceContext.repositories().agvDock();



    public boolean validateData(String agvId, String briefDescription, String model, String maxWeightCapacity, String range) {
        if(agvId == null || briefDescription == null || model == null || maxWeightCapacity == null || range == null ||
                agvId.length()!=8 || briefDescription.length()>30 || model.length()>50 || Double.parseDouble(maxWeightCapacity)<=0 || Double.parseDouble(range)<=0) return false;
        return true;
    }

    public AGV configureAGV(final String agvId, final String briefDescription, final String model,
                            final Double maxWeightCapacity, final Double range, final String position ) {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.WAREHOUSE_EMPLOYEE);

        final AGVBuilder newAGV = new AGVBuilder()
                .withId(new AGVId(agvId))
                .withBriefDescription(new BriefDescription(briefDescription))
                .withModel(new Model(model))
                .withMaxWeightCapacity(new MaxWeightCapacity(maxWeightCapacity))
                .withRange(new Range(range))
                .withPosition(new AGVPosition(position));

        AGV agv = newAGV.build();


        return agvRepository.save(agv);
    }
}
