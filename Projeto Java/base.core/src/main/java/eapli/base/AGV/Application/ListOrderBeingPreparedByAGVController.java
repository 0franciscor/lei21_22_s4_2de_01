package eapli.base.AGV.Application;

import eapli.base.AGV.Domain.AGV;
import eapli.base.AGV.Repositories.AGVRepository;
import eapli.base.AGV.dto.AgvDto;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.ProductOrder;
import eapli.base.ordermanagement.dto.OrderDto;
import eapli.base.ordermanagement.repository.OrderRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ListOrderBeingPreparedByAGVController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final TransactionalContext context = PersistenceContext.repositories().newTransactionalContext();
    private final AGVRepository agvRepository = PersistenceContext.repositories().agv(context);
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders(context);


    public List<AgvDto> getAGVsAvaiable(){
        List<AgvDto> agvsAvaiable = new ArrayList<>();

        Iterable<AGV> agvsIterable = agvRepository.getAGVsAvaiable();

        for (AGV agv : agvsIterable){

            agvsAvaiable.add(new AgvDto(agv.getAgvId().getAGVId()));

        }

        return agvsAvaiable;

    }

    /*public List<OrderDto> getOrdersWhoNeedToBePreparedByAGV(String idAgv){
        List<OrderDto> orderDtos = new ArrayList<>();

        Iterable<ProductOrder> orders = orderRepository.getOrdersWhoNeedToBePrepared();

        Optional<AGV> agv = agvRepository.getAGVById(idAgv);

        context.beginTransaction();

        agvRepository.changeStatusOfAGV(agv.get());





    }*/
}
