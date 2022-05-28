package eapli.base.app.backoffice.console.warehouseEmployeeManagement;

import eapli.base.AGV.application.ListOrderBeingPreparedByAGVController;
import eapli.base.AGV.dto.AgvDto;
import eapli.base.app.backoffice.console.ordermanagement.ProductOrderDtoPrinter;
import eapli.base.productmanagement.dto.ProductOrderDto;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.ArrayList;
import java.util.List;

public class ListOrderBeingPreparedByAGVUI extends AbstractUI {

    ListOrderBeingPreparedByAGVController listOrderBeingPreparedByAGVController = new ListOrderBeingPreparedByAGVController();

    @Override
    protected boolean doShow() {

        final SelectWidget<AgvDto> selector = new SelectWidget<>("AGV's Avaiable:", listOrderBeingPreparedByAGVController.getAGVsAvaiable(), new AgvDtoPrinter());
        selector.show();

        AgvDto agvDto = selector.selectedElement();

        String taskDescription;

        do {
            taskDescription = Console.readLine("Enter the description that is associated with the task assigned to the selected AGV:");
        }while (taskDescription == null);

        List<ProductOrderDto> orders = null;
        try {
            orders = listOrderBeingPreparedByAGVController.getOrdersWhoNeedToBePreparedByAGV(agvDto.id, taskDescription);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.printf("\n-----* Information about Orders *-----\n");

        if (orders != null){
            for (ProductOrderDto p : orders){

                System.out.printf("\n-----> Order number %d <-----\n", p.orderId);
                System.out.printf("AGV ID assigned to this order -> %s\n", p.agvId);
                System.out.printf("Description of the task assigned to the AGV -> %s\n", p.taskDescription);
                System.out.printf("Order Status -> %s\n", p.status);

            }
        }

        int option;
        List<ProductOrderDto> orders1 = new ArrayList<>();
        if (orders.size() != 0){
            do {
                final SelectWidget<ProductOrderDto> selector1 = new SelectWidget<>("Orders:", orders, new ProductOrderDtoPrinter());
                selector1.show();

                ProductOrderDto productOrderDto = selector1.selectedElement();
                orders1.add(productOrderDto);
                System.out.println("Do you want to select any more orders?");
                System.out.println("1 - yes");
                System.out.println("2 - no");
                option = Console.readOption(1, 2, 0);

            } while (option == 1);

            List<ProductOrderDto> orders3 = listOrderBeingPreparedByAGVController.changeTheStatusOfOrdersForDispatchedToCustomer(orders1);

            for (ProductOrderDto p : orders3){

                System.out.printf("\n-----> Order number %d <-----\n", p.orderId);
                System.out.printf("AGV ID assigned to this order -> %s\n", p.agvId);
                System.out.printf("Order Status -> %s\n", p.status);

            }
        }
        else System.out.println("!WARNING! - There is no order in the system that needs to be prepared by an AGV");


        return false;
    }

    @Override
    public String headline() {
        return "List Orders being prepared by an AGV";
    }
}
