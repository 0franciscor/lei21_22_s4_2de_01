package eapli.base.app.user.console.presentation.productOrder;

import eapli.base.ordermanagement.application.ListOpenOrdersController;
import eapli.base.ordermanagement.dto.ProductOrderDto;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

public class ListOpenOrdersUI extends AbstractUI {

    private final ListOpenOrdersController controller = new ListOpenOrdersController();


    @Override
    protected boolean doShow() {


       Long clientId = 3L;
       // get orders
       List<ProductOrderDto> list = controller.getOpenOrdersOfAClient(clientId);
       return true;


    }

    @Override
    public String headline() {
        return "Client's Open Orders";
    }

}
