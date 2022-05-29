package eapli.base.app.backoffice.console.taskManagement;

import eapli.base.AGV.application.CallAGVManagerController;
import eapli.base.AGV.application.TaskManagement;
import eapli.framework.presentation.console.AbstractUI;

import java.io.IOException;

public class TaskManagementUI extends AbstractUI {

    private final CallAGVManagerController controller = new CallAGVManagerController();

    @Override
    protected boolean doShow() {
        try {
            controller.connectDaemon("localhost", 8890);
            controller.getAGVInfo();
        } catch (IOException e) {
            System.out.println("There was an error when executing the Algorithm Call");
        }
        controller.callFIFO();
        System.out.println("The automatic task assignment service has been called");
        try {
            controller.closeConnection();
        } catch (IOException e) {
            System.out.println("There was an error when closing the connection");
        }
        return true;
    }

    @Override
    public String headline() {
        return "Automatically assign Tasks to AGV's";
    }
}
