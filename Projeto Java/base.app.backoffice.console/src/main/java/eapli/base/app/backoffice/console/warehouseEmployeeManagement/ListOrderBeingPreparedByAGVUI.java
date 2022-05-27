package eapli.base.app.backoffice.console.warehouseEmployeeManagement;

import eapli.base.AGV.application.ListOrderBeingPreparedByAGVController;
import eapli.base.AGV.dto.AgvDto;
import eapli.base.ordermanagement.dto.ProductOrderDto;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

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

        return false;
    }

    @Override
    public String headline() {
        return "List Orders being prepared by an AGV";
    }
}
