package eapli.base.ordermanagement.application;

<<<<<<< HEAD
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import javax.persistence.PersistenceContext;
=======
import eapli.base.clientmanagement.domain.Client;
import eapli.base.clientmanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.AdditionalComment;
import eapli.base.ordermanagement.domain.Address;
import eapli.base.ordermanagement.domain.ProductOrder;
import eapli.base.ordermanagement.domain.Shipment;
import eapli.base.ordermanagement.repository.OrderRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.*;
>>>>>>> 35e3cbd956197eaf592d1138f4305fb3bae8f549

public class RegisterClientOrderController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ClientRepository clientRepository = PersistenceContext.repositories().client();
<<<<<<< HEAD
=======
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    private Client client;



    public ProductOrder registerOrder(List<List<String>> addresses, Shipment shipment, ProductOrder.SourceChannel sourceChannel, Calendar interactionDate, String additionalComment){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.SALES_CLERK);
        ProductOrder order = new ProductOrder(client, new Address(addresses.get(0).get(0), addresses.get(0).get(1),addresses.get(0).get(2), addresses.get(0).get(3), addresses.get(0).get(4)), new Address(addresses.get(1).get(0), addresses.get(1).get(1),addresses.get(1).get(2), addresses.get(1).get(3), addresses.get(1).get(4)),shipment,sourceChannel,interactionDate,new AdditionalComment(additionalComment), authz.session().get().authenticatedUser());
        return orderRepository.save(order);
    }

    public ProductOrder registerOrder(List<List<String>> addresses, Shipment shipment, ProductOrder.SourceChannel sourceChannel, Calendar interactionDate){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.SALES_CLERK);
        ProductOrder order = new ProductOrder(client, new Address(addresses.get(0).get(0), addresses.get(0).get(1),addresses.get(0).get(2), addresses.get(0).get(3), addresses.get(0).get(4)), new Address(addresses.get(1).get(0), addresses.get(1).get(1),addresses.get(1).get(2), addresses.get(1).get(3), addresses.get(1).get(4)),shipment,sourceChannel,interactionDate, authz.session().get().authenticatedUser());
        return orderRepository.save(order);
    }


    public boolean verifyClientById(Long clientId) {
        Optional<Client> chosenClient = clientRepository.ofIdentity(clientId);
        if(chosenClient.isPresent()) {
            client = chosenClient.get();
        }
        return chosenClient.isPresent();
    }
>>>>>>> 35e3cbd956197eaf592d1138f4305fb3bae8f549
}
