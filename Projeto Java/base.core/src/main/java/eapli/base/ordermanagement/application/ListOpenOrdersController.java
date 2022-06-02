package eapli.base.ordermanagement.application;

import eapli.base.ordermanagement.domain.ProductOrder;
import eapli.base.ordermanagement.dto.ProductOrderDto;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.ArrayList;
import java.util.List;

public class ListOpenOrdersController {


    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final ListOpenOrdersService service = new ListOpenOrdersService();

    public List<ProductOrderDto> getOpenOrdersOfAClient(Long clientId){

        List<ProductOrderDto> list = new ArrayList<>();
        Iterable<ProductOrder> po = service.getOpenOrdersOfTheClient();

        for (ProductOrder productOrder : po) {
            Long id = productOrder.getOrderId();
            String client = productOrder.obtainClient().toString();
            String total = productOrder.obtainTotalAmountWithTaxes().toString();
            String status = productOrder.getOrderStatus().toString();
            ProductOrderDto productOrderDto = new ProductOrderDto(id, client, total, status);
            list.add(productOrderDto);
        }
        return list;
    }






}
