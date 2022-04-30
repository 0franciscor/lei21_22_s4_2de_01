package eapli.base.productmanagement.application;


import eapli.base.infrastructure.persistence.PersistenceContext;

import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.UniqueInternalCode;

import eapli.base.productmanagement.repository.ProductRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Optional;

@ApplicationService
public class ListProductService {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ProductRepository productRepository = PersistenceContext.repositories().products();

    /**
     *
     * @return
     */
    public Product findProductByUniqueInternalCode(UniqueInternalCode code) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.SALES_CLERK);
        return productRepository.findByUniqueInternalCode(code);
    }
}
