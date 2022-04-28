package eapli.base.warehouse.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehouse.domain.JsonImport;
import eapli.base.warehouse.domain.Warehouse;
import eapli.base.warehouse.repositories.WarehouseRepository;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

public class ImportJsonController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final WarehouseRepository warehouseRepository = PersistenceContext.repositories().warehouse();

    private final JsonImport jsonImport = new JsonImport();

    public ImportJsonController(){}

    public boolean importWarehouse(final String jsonName){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE);
        Warehouse warehouse = null;
        warehouseRepository.save(warehouse);

        return true;
    }
}
