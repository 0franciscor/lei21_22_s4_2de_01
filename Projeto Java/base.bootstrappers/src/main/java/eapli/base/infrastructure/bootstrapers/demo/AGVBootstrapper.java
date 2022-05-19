package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.AGV.Application.ConfigureAGVController;
import eapli.base.warehouse.domain.AGVDock;
import eapli.framework.actions.Action;

import java.util.List;

public class AGVBootstrapper extends BaseDemoBootstrapper implements Action {

    private final ConfigureAGVController configureAGVController = new ConfigureAGVController();

    @Override
    public boolean execute() {
        createAGV();
        return true;
    }

    private boolean createAGV(){
        List<AGVDock> list = (List<AGVDock>) configureAGVController.configureAGVDock();
        AGVDock dock = list.get(0);

        configureAGVController.configureAGV("12345678","abcdefg","ASD56S",30.0,35.0,5.0,"x",dock,"FREE","123");
        return true;
    }





}
