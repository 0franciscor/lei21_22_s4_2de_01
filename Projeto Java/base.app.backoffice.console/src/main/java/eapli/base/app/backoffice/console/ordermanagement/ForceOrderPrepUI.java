package eapli.base.app.backoffice.console.ordermanagement;

import eapli.base.AGV.Domain.AGV;
import eapli.base.AGV.Domain.AGVId;
import eapli.base.app.backoffice.console.clientmanagement.RegisterClientUI;
import eapli.base.app.backoffice.console.productmanagement.ListProductUi;
import eapli.base.ordermanagement.application.ForceOrderPrepController;
import eapli.base.ordermanagement.application.RegisterClientOrderController;
import eapli.base.ordermanagement.domain.ProductOrder;
import eapli.base.ordermanagement.domain.Shipment;
import eapli.base.ordermanagement.dto.ProductOrderDto;
import eapli.base.productmanagement.domain.UniqueInternalCode;
import eapli.base.warehouse.domain.AGVDock;
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

    @Override
    protected boolean doShow() {

        // escolher order
        List<ProductOrderDto> list = forceOrderController.getOrdersToBePrepared();
        System.out.println(list);
        int index = 1;
        for(ProductOrderDto po: list) {
            System.out.println(index + " - " + po.orderId + " - " + po.customerName + " - " + po.date + " - " + po.total);
            index++;
        }
        int b = Integer.parseInt(Console.readLine("Choose an Order"));
        ProductOrderDto productOrder = list.get(b-1);

        // save order
        forceOrderController.saveOrder(productOrder.orderId);


        // escolher agv
        List<AGV> list1 = forceOrderController.showAvailableAGVs();
        int index1 = 1;
        for(AGV agv: list1) {
            System.out.println(index + " - " + agv.getAgvId());
            index1++;
        }
        int a = Integer.parseInt(Console.readLine("Choose an agv"));
        AGV agv = list1.get(a-1);

        // save AGV
        forceOrderController.saveAGV(agv.getAgvId());

        // force order prep
        forceOrderController.forceOrderPrep();

        return true;

    }

    @Override
    public String headline() {
        return "Force Order Prep";
    }

}
