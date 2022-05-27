package eapli.base.app.backoffice.console.ordermanagement;

import eapli.base.AGV.dto.AgvDto;
import eapli.base.ordermanagement.application.ForceOrderPrepController;
import eapli.base.ordermanagement.domain.ProductOrder;
import eapli.base.productmanagement.dto.ProductOrderDto;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.*;

/**
 * UI for register a client order to the application.
 *
 * Created by 1201382@isep.ipp.pt
 */
public class ForceOrderPrepUI extends AbstractUI {

    private final ForceOrderPrepController forceOrderController = new ForceOrderPrepController();

    private Boolean operationError=false;


    @Override
    protected boolean doShow() {

        do{
            try {

                // escolher order

                System.out.println("Select the order you want to force:");
                List<ProductOrderDto> list = forceOrderController.getOrdersToBePrepared();
                if(list.isEmpty()){
                    System.out.println("\nThere are no orders needing preparation.");
                    operationError=true;
                }
                int index = 1;
                for(ProductOrderDto po: list) {
                    System.out.println(index + " - " + po.orderId + " - " + po.customerName + " - " + po.date + " - " + po.total);
                    index++;
                }
                int order = Integer.parseInt(Console.readLine("Choose an Order"));
                ProductOrderDto productOrder = list.get(order-1);


                System.out.println("##po##");
                System.out.println(productOrder.getProductOrderId());


                // escolher agv
                List<AgvDto> list1 = forceOrderController.showAvailableAGVs();
                int index1 = 1;
                for(AgvDto agv: list1) {
                    System.out.println(index1 + " - id:" + agv.getId() + " - volume:" + agv.getVolume() + " - weight:" + agv.getWeight());
                    index1++;
                }
                int a = Integer.parseInt(Console.readLine("Choose an agv"));
                AgvDto agv = list1.get(a-1);

                System.out.println("####");
                System.out.println(productOrder.getProductOrderId());
                System.out.println(agv.getId());
                System.out.println("####");

                // force order prep
                forceOrderController.forceOrderPrep(productOrder.getProductOrderId(), agv.getId());

            } catch(Exception e){
                System.out.println(e.getMessage());
                operationError=true;
            }

        } while(operationError);

        return true;

    }

    @Override
    public String headline() {
        return "Force Order Prep";
    }

}
