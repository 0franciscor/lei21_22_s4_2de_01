package eapli.base.ordermanagement.application;


import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.*;
import eapli.base.productmanagement.application.ListProductService;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.UniqueInternalCode;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.clientmanagement.repositories.ClientRepository;

import eapli.base.usermanagement.domain.BaseRoles;


import java.util.*;


public class RegisterClientOrderController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ClientRepository clientRepository = PersistenceContext.repositories().client();
    private ListProductService listProductService = new ListProductService();

    private Client client;
    private Set<OrderItem> orderItems = new HashSet<>();



    public ProductOrder registerOrder(Map<String,Integer> items, List<List<String>> addresses, Shipment shipment, ProductOrder.SourceChannel sourceChannel, Calendar interactionDate, String additionalComment, ProductOrder.Payment payment){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.SALES_CLERK);
        fillOrderItems(items,orderItems);
        ProductOrder order = new ProductOrder(client, new Address(addresses.get(0).get(0), addresses.get(0).get(1),addresses.get(0).get(2), addresses.get(0).get(3), addresses.get(0).get(4)), new Address(addresses.get(1).get(0), addresses.get(1).get(1),addresses.get(1).get(2), addresses.get(1).get(3), addresses.get(1).get(4)),shipment,sourceChannel,interactionDate,new AdditionalComment(additionalComment), authz.session().get().authenticatedUser(),orderItems,payment);
        PersistenceContext.repositories().orders().save(order);
        return order;
    }

    public ProductOrder registerOrder(Map<String,Integer> items,List<List<String>> addresses, Shipment shipment, ProductOrder.SourceChannel sourceChannel, Calendar interactionDate, ProductOrder.Payment payment){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.SALES_CLERK);
        fillOrderItems(items,orderItems);
        ProductOrder order = new ProductOrder(client, new Address(addresses.get(0).get(0), addresses.get(0).get(1),addresses.get(0).get(2), addresses.get(0).get(3), addresses.get(0).get(4)), new Address(addresses.get(1).get(0), addresses.get(1).get(1),addresses.get(1).get(2), addresses.get(1).get(3), addresses.get(1).get(4)),shipment,sourceChannel,interactionDate, authz.session().get().authenticatedUser(),orderItems,payment);
        PersistenceContext.repositories().orders().save(order);
        return order;
    }


    public boolean verifyClientById(Long clientId) {
        Optional<Client> chosenClient = clientRepository.ofIdentity(clientId);
        if(chosenClient.isPresent()) {
           client = chosenClient.get();
        }
        return chosenClient.isPresent();
    }

    public boolean verifyProductById(UniqueInternalCode code) {
        Product chosenProduct = listProductService.findProductByUniqueInternalCode(code);

        return chosenProduct != null;
    }

    private void fillOrderItems(Map<String, Integer> items, Set<OrderItem> setItems) {

        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            String code = entry.getKey();
            Integer quantity = entry.getValue();
            Product product = listProductService.findProductByUniqueInternalCode(UniqueInternalCode.valueOf(code));
            OrderItem orderItem = new OrderItem(quantity, product);
            setItems.add(orderItem);
            PersistenceContext.repositories().orderItems().save(orderItem);
        }

    }


}
