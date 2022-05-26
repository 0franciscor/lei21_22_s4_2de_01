package eapli.base.ordermanagement.application;



import eapli.base.AGV.Application.ConfigureAGVController;
import eapli.base.AGV.Domain.AGV;
import eapli.base.AGV.Domain.AGVId;
import eapli.base.AGV.Domain.AGVStatus;
import eapli.base.AGV.Repositories.AGVRepository;
import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.ProductOrder;
import eapli.base.ordermanagement.dto.ProductOrderDto;
import eapli.base.ordermanagement.repository.OrderRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.base.infrastructure.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.Iterator;
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
            if(agv.getAgvStatus().equals(AGVStatus.FREE)){
                list.add(agv);
            }
        }
        return list;
    }

    public List<ProductOrderDto> getOrdersToBePrepared(){
        Iterable<ProductOrder> aux = orderRepository.findAll();
        Iterator<ProductOrder> it = aux.iterator();
        List<ProductOrderDto> list = new ArrayList<>();

        OrderStatus.Status st = OrderStatus.Status.TO_BE_PREPARED;
        while(it.hasNext()){
            ProductOrder productOrder = it.next();

            if(productOrder.getOrderStatus().obtainStatus().equals(st)) {
                Long id = productOrder.getOrderId();
                String date = productOrder.obtainInteractionDate().toString();
                String name = productOrder.obtainClient().obtainName().toString();
                String orderTotal = productOrder.obtainTotalAmountWithTaxes().toString();
                ProductOrderDto productOrderDto = new ProductOrderDto(id, date, name, orderTotal);
                list.add(productOrderDto);
            }
        } return list;
    }


    public ProductOrder forceOrderPrep(){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE, BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.SALES_CLERK);
        txCtx.beginTransaction();

        try{
            // orderRepository.changeTheStatusOfTheOrderToBeingPreparedOnWarehouse(productOrder))
            OrderStatus.Status st = OrderStatus.Status.BEING_PREPARED_ON_WAREHOUSE;
            OrderStatus orderStatus = new OrderStatus(st);
            this.productOrder.changeProductOrderStatus(orderStatus);
            orderRepository.save(this.productOrder);

            //update agv status
            agv.changeStatusOfAGVForOccupied();
            productOrder.preparedByAGV(agv);
            agvRepository.save(this.agv);

            txCtx.commit();
        } catch (Exception e){
            txCtx.rollback();
        }
        return this.productOrder;

    }



}
