package eapli.base.app.backoffice.console.warehouseEmployeeManagement;

import eapli.base.AGVManagement.Application.ConfigureAGVController;
import eapli.base.AGVManagement.Domain.AGVId;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;


/**
 * UI for configuring a warehouse AGV
 *
 * Created by Pedro Rocha
 */
public class ConfigureAGVUI extends AbstractUI {
    private final ConfigureAGVController configureAGVController = new ConfigureAGVController();


    @Override
    protected boolean doShow() {

        final String agvId = Console.readLine("AGV Id:");
        final String briefDescription = Console.readLine("Brief Description:");
        final String model = Console.readLine("Model:");
        final String maxWeightCapacity = Console.readLine("Max Weight Capacity:");
        final String range = Console.readLine("Range:");

        boolean flag = configureAGVController.validateData(agvId, briefDescription, model, maxWeightCapacity, range);
        if(flag==false){
            throw new IllegalArgumentException();
        }
        configureAGVController.configureAGV(agvId,briefDescription,model,Double.parseDouble(maxWeightCapacity),Double.parseDouble(range));
        return true;
    }

    @Override
    public String headline() {
        return "Register an AGV";
    }

}
