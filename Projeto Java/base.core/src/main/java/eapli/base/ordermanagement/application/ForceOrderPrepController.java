package eapli.base.ordermanagement.application;



import eapli.base.AGV.Application.ConfigureAGVController;
import eapli.base.AGV.Domain.AGV;
import eapli.base.AGV.Domain.AGVId;
import eapli.base.AGV.Domain.ChangeAGVStatus;
import eapli.base.AGV.Repositories.AGVRepository;
import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.ProductOrder;
import eapli.base.ordermanagement.repository.OrderRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.base.infrastructure.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;


public class ForceOrderPrepController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AGVRepository agvRepository = PersistenceContext.repositories().agv();
    private final OrderRepository orderRepository= PersistenceContext.repositories().orders();
    private final ConfigureAGVController configureAGVController = new ConfigureAGVController();

    private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();

    private ProductOrder productOrder;
    private AGV agv;

    public void saveOrder(Long orderId){
        orderRepository.getProductOrderById(orderId);
    }

    public void saveAGV(AGVId agvId){
        agv.changeAGVId(agvId);
    }

    public List<AGV> showAvailableAGVs(){
        Iterable<AGV> aux = agvRepository.findAll();
        List<AGV> list = new ArrayList<>();
        for(AGV agv : aux ){
            if(agv.getStatus().equals("Is Free")){
                list.add(agv);
            }
        }
        return list;
    }

    public List<ProductOrder> getOrdersToBePrepared(){
        Iterable<ProductOrder> aux = orderRepository.findAll();
        List<ProductOrder> list = new ArrayList<>();
        OrderStatus.Status st = OrderStatus.Status.BEING_PREPARED_ON_WAREHOUSE;
        for(ProductOrder productOrder : aux ){
            if(productOrder.getOrderStatus().equals(st)){
                list.add(productOrder);
            }
        }
        return list;
    }

    public ProductOrder forceOrderPrep(){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE, BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.SALES_CLERK);
        txCtx.beginTransaction();

        try{
            // orderRepository.changeTheStatusOfTheOrderToBeingPreparedOnWarehouse(productOrder))
            OrderStatus.Status st = OrderStatus.Status.BEING_PREPARED_ON_WAREHOUSE;
            OrderStatus orderStatus = new OrderStatus(st);
            this.productOrder.changeProductOrderStatus(orderStatus);
            //productOrder.preparedByAGV(agv); // TIRAR DE COMENTÁRIO QUANDO ATUALIZAR A PRODUCT ORDER
            orderRepository.save(this.productOrder);

            //update agv status
            ChangeAGVStatus agvStatus = new ChangeAGVStatus("Is Occupied Serving");
            this.agv.changeAGVStatus(agvStatus);
            agvRepository.save(this.agv);

            txCtx.commit();
        } catch (Exception e){
            txCtx.rollback();
        }
        return this.productOrder;

    }



}
