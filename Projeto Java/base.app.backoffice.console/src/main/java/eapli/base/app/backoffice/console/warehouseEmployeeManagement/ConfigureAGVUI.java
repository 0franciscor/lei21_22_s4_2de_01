package eapli.base.app.backoffice.console.warehouseEmployeeManagement;

import eapli.base.AGV.Application.ConfigureAGVController;
import eapli.base.clientmanagement.domain.Client;
import eapli.base.ordermanagement.domain.Address;
import eapli.base.ordermanagement.domain.ProductOrder;
import eapli.base.ordermanagement.domain.Shipment;
import eapli.base.warehouse.domain.AGVDock;
import eapli.framework.infrastructure.authz.domain.model.Name;
import eapli.framework.infrastructure.authz.domain.model.Password;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.time.util.Calendars;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * UI for configuring a warehouse AGV
 *
 * Created by Pedro Rocha
 */
public class ConfigureAGVUI extends AbstractUI {
    private final ConfigureAGVController configureAGVController = new ConfigureAGVController();


    @Override
    protected boolean doShow() {

        final String agvId = Console.readLine("AGV Id: (8 chars)");
        final String briefDescription = Console.readLine("Brief Description: (30max)");
        final String model = Console.readLine("Model: (50max)");
        final String maxWeightCapacity = Console.readLine("Max Weight Capacity: (kg)");
        final String maxVolumeCapacity = Console.readLine("Max Volume Capacity: (cm3)");
        final String range = Console.readLine("Range: (hours)");
        final String position = Console.readLine("Position:");
        final String status = "FREE";


        boolean flag = configureAGVController.validateData(agvId, briefDescription, model, maxWeightCapacity, maxVolumeCapacity, range, position);
        if(!flag){
            throw new IllegalArgumentException();
        }

        List<AGVDock> list = (List<AGVDock>) configureAGVController.configureAGVDock();
        int index = 1;
        for(AGVDock agvDock: list) {
            System.out.println(index + " - " + agvDock.getId());
            index++;
        }
        int a = Integer.parseInt(Console.readLine("Choose an agvdock"));
        AGVDock dock = list.get(a-1);

        Boolean flag1 = false;
        if(configureAGVController.configureAGV(agvId,briefDescription,model,Double.parseDouble(maxWeightCapacity), Double.parseDouble(maxVolumeCapacity), Double.parseDouble(range), position, dock, status)==null){
            System.out.println("AGV Dock occupied");
            flag1 = true;

        }
        if(!flag1){
            System.out.println("*================== AGV Created ================*");
            System.out.printf("AGVId: %s; \nBrief Description: %s; \nModel: %s \nMax Weight Capacity: %s \nMax Volume Capacity: %s \nRange: %s \nPosition: %s \nDock ID: %s\nStatus: %s\nTask: %s\n",
                    agvId,briefDescription,model,maxWeightCapacity,maxVolumeCapacity,range,position,dock.getId(),status);
        }

        return true;
    }

    @Override
    public String headline() {
        return "Register an AGV";
    }

}
