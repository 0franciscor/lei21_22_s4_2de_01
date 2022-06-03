package eapli.base.app.backoffice.console.ordermanagement;

import eapli.base.ordermanagement.application.ViewOrdersSentToCustomerController;
import eapli.base.ordermanagement.dto.OrderDTO;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.Iterator;

public class ViewOrdersSentToCustomerUI extends AbstractUI {

    private final ViewOrdersSentToCustomerController viewOrdersSentToCustomerController = new ViewOrdersSentToCustomerController();

    @Override
    protected boolean doShow() {
        System.out.println("Encomendas no Estado DISPATCHED FOR COSTUMER");

        Iterator<OrderDTO> orders = viewOrdersSentToCustomerController.getOrdersDispatchedForCustomerDelivery().iterator();

        while (orders.hasNext()) {
            OrderDTO order = orders.next();
            System.out.println(order);
        }
        System.out.println();

        String option = Console.readLine("Deseja alterar o estado de alguma encomenda para DELIVERED BY CARRIER?\n(sim|não)\n");

        while (option.equalsIgnoreCase("sim")){
            String orderId = Console.readLine("Order ID:");

            viewOrdersSentToCustomerController.changeStatusToBeingDelivered(orderId);

            option = Console.readLine("Deseja alterar o estado de mais alguma encomenda?\n(sim|não)\n");
        }

        return true;
    }

    @Override
    public String headline() {
        return "Visualizar Encomendas no estado DISPATCHED FOR COSTUMER";
    }
}
