package eapli.base.app.backoffice.console.warehouseEmployeeManagement;

import eapli.base.AGV.application.DashboardController;
import eapli.framework.presentation.console.AbstractUI;

import java.io.IOException;

public class DashboardUI extends AbstractUI {

    private final DashboardController theController = new DashboardController();


    @Override
    protected boolean doShow() {

        try {
            System.out.println("MANUUU");
            theController.runDashboard();
            System.out.println("ELAAAA");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public String headline() {
        return "Opening dashboard";
    }
}
