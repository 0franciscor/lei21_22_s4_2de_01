package eapli.base.AGV.application;

import eapli.base.AGV.domain.AGV;
import eapli.base.AGV.repositories.AGVRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;


public class DashBoardController {

    AGVRepository agvRepository = PersistenceContext.repositories().agv();

    public AGV getAGVsInformationForDashBoard(String agvID){
        System.out.println("aqui cheguei");
        return agvRepository.getAGVByIdSt(agvID);
    }
}
