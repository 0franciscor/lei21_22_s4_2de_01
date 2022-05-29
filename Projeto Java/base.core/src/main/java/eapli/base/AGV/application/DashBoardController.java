package eapli.base.AGV.application;

import java.io.IOException;

public class DashboardController {
    private final DashboardService service = new DashboardService();

    public void runDashboard() throws IOException {
        service.openDashboard();
    }
}
