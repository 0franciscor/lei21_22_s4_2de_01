package eapli.base.AGV.application;

import eapli.base.AGV.domain.AGV;
import eapli.base.AGV.repositories.AGVRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;


public class DashBoardController {

    private AGVRepository agvRepository = PersistenceContext.repositories().agv();

    public Iterable<AGV> getAGVsInformationForDashBoard(){
        return agvRepository.findAll();

    }

}
