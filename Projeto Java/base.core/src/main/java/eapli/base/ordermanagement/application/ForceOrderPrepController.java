package eapli.base.ordermanagement.application;
import eapli.base.AGV.dto.AgvDto;
import eapli.base.AGV.application.ConfigureAGVController;
import eapli.base.AGV.domain.AGV;
import eapli.base.AGV.domain.AGVStatus;
import eapli.base.AGV.repositories.AGVRepository;
import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.ProductOrder;
import eapli.base.ordermanagement.repository.OrderRepository;
import eapli.base.productmanagement.dto.ProductOrderDto;
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

    public ProductOrder getOrder(Long orderId){
        return orderRepository.getOrderById(orderId);

    }

    public AGV getAGV(String agvId){
        System.out.println("entrou: " + agvId);
        return agvRepository.getAGVById(agvId);
    }


    public List<AgvDto> showAvailableAGVs(){
        Iterable<AGV> aux = agvRepository.findAll();
        List<AgvDto> list = new ArrayList<>();
        for(AGV agv : aux ){
            if(agv.getAgvStatus().equals(AGVStatus.FREE)){
                String id = agv.getAgvId().toString();
                String volume = agv.getMaxVolumeCapacity().toString();
                String weight = agv.getMaxWeightCapacity().toString();
                AgvDto dto = new AgvDto(id,volume,weight);
                list.add(dto);
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


    public ProductOrder forceOrderPrep(String productOrderid, String agvid){

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE, BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.SALES_CLERK);
        txCtx.beginTransaction();

        try{
            // orderRepository.changeTheStatusOfTheOrderToBeingPreparedOnWarehouse(productOrder));
            OrderStatus.Status st = OrderStatus.Status.BEING_PREPARED_BY_AGV;
            OrderStatus orderStatus = new OrderStatus(st);
            System.out.println("product id em string:" + productOrderid);
            System.out.println("a");
            System.out.println("a" + Long.parseLong(productOrderid) );
            System.out.println("b");
            ProductOrder order = getOrder(Long.parseLong(productOrderid));
            System.out.println("c");
            order.changeProductOrderStatus(orderStatus);
            System.out.println("d");

            //update agv status
            System.out.println("d2");
            AGV agv1 = getAGV(agvid);
            System.out.println("e");
            System.out.println("agv id em string:" + agvid);
            System.out.println("f");
            agv1.changeStatusOfAGVForOccupied();
            System.out.println("g");
            order.preparedByAGV(agv);
            System.out.println("h");
            System.out.println(productOrderid);
            System.out.println("i");
            System.out.println(agvid);
            System.out.println("j");
            agvRepository.save(agv1);
            System.out.println("k");
            orderRepository.save(order);
            System.out.println("l");

            txCtx.commit();
        } catch (Exception e){
            txCtx.rollback();
        }
        return this.productOrder;

    }



}
