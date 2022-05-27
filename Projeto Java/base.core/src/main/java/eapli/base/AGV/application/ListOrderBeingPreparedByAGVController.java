package eapli.base.AGV.application;

import eapli.base.AGV.domain.AGV;
import eapli.base.AGV.domain.AGVTask;
import eapli.base.AGV.repositories.AGVRepository;
import eapli.base.AGV.dto.AgvDto;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.ProductOrder;
import eapli.base.ordermanagement.dto.ProductOrderDto;
import eapli.base.ordermanagement.repository.OrderRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.ArrayList;
import java.util.List;


public class ListOrderBeingPreparedByAGVController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final TransactionalContext context = PersistenceContext.repositories().newTransactionalContext();
    private final AGVRepository agvRepository = PersistenceContext.repositories().agv(context);
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders(context);


    public List<AgvDto> getAGVsAvaiable(){

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);
        List<AgvDto> agvsAvaiable = new ArrayList<>();

        Iterable<AGV> agvsIterable = agvRepository.getAGVsAvaiable();

        for (AGV agv : agvsIterable){

            agvsAvaiable.add(new AgvDto(agv.getAgvId().getAGVId()));

        }

        return agvsAvaiable;

    }

    public List<ProductOrderDto> getOrdersWhoNeedToBePreparedByAGV(String idAgv, String taskDescription) throws Exception {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);
        int count = 0;

        List<ProductOrderDto> orderDtos = new ArrayList<>();

        Iterable<ProductOrder> orders = orderRepository.getOrdersWhoNeedToBePrepared();

        AGV agv = agvRepository.getAGVById(idAgv);

        context.beginTransaction();

        agv.assignATaskForAGV(new AGVTask(taskDescription));
        agv.changeStatusOfAGVForOccupied();

        for (ProductOrder p : orders){

            p.changeStatusOfOrderToBeingPreparedByAnAGV();
            try {
                agv.addOrdersToATask(taskDescription, p);
            }catch (Exception e){
                agv.assignATaskForAGV(new AGVTask(taskDescription + " " +  count));
                agv.addOrdersToATask(taskDescription, p);
                count++;
            }

            agvRepository.save(agv);
            orderRepository.save(p);
            orderDtos.add(new ProductOrderDto(agv.getAgvId().getAGVId(), p.getOrderId(), taskDescription + " " + count, p.getStatus().obtainStatus().name()));
        }

        context.commit();

        return orderDtos;

    }

    public List<ProductOrderDto>
}
