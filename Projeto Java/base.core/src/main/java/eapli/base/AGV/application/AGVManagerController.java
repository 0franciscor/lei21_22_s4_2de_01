package eapli.base.AGV.application;

import eapli.base.AGV.domain.AGV;


public interface AGVManagerController {

    boolean assignTasks();

    Iterable<AGV> getAGVsInformationForDashboard();
}
