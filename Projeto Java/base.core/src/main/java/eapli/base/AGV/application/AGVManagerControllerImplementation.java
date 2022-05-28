package eapli.base.AGV.Application;

import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class AGVManagerControllerImplementation implements AGVManagerController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final TaskManagement taskManagement = new TaskManagement();

    public AGVManagerControllerImplementation(){}

    @Override
    public boolean assignTasks() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE, BaseRoles.POWER_USER);
        return taskManagement.assignTasks();
    }
}
