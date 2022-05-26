package eapli.base.AGV.Domain;

import eapli.base.ordermanagement.domain.ProductOrder;
import eapli.base.warehouse.domain.AGVDock;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;
import java.util.List;

@Entity
public class AGV implements AggregateRoot<AGVId> {

    @EmbeddedId
    private AGVId agvId;

    @OneToOne
    private AGVDock agvDock;

    @Embedded
    private Range range;

    @Embedded
    private MaxWeightCapacity maxWeightCapacity;

    @Embedded
    private MaxVolumeCapacity maxVolumeCapacity;

    @Embedded
    private Model model;

    @Embedded
    private BriefDescription briefDescription;

    @Embedded
    private AGVPosition position;

    @Enumerated
    private AGVStatus agvStatus;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "task_id")
    private List<AGVTask> agvTask;


    public AGV(){}

    public AGV(final AGVId agvId, final BriefDescription briefDescription, final Model model, final MaxWeightCapacity maxWeightCapacity, final MaxVolumeCapacity maxVolumeCapacity, final Range range, final AGVPosition position, final AGVDock agvDock){
        this.agvId = agvId;
        this.range = range;
        this.maxWeightCapacity = maxWeightCapacity;
        this.maxVolumeCapacity = maxVolumeCapacity;
        this.model = model;
        this.briefDescription = briefDescription;
        this.position = position;
        this.agvDock = agvDock;
        this.agvStatus = AGVStatus.FREE;
    }

    @Override
    public boolean sameAs(Object other) {
        if(other == null) return false;
        if(this == other) return true;

        eapli.base.AGV.Domain.AGV newObj = ((eapli.base.AGV.Domain.AGV) other);

        return agvId == newObj.agvId && range == newObj.range && maxWeightCapacity == newObj.maxWeightCapacity && maxVolumeCapacity == newObj.maxVolumeCapacity
                && model == newObj.model && briefDescription == newObj.briefDescription && position == newObj.position && agvDock == newObj.agvDock && agvTask == newObj.agvTask;
    }

    public AGVDock agvDock() {
        return agvDock;
    }

    @Override
    public AGVId identity() {
        return agvId;
    }

    public AGVId getAgvId() {
        return agvId;
    }

    public AGVDock getAgvDock() {
        return agvDock;
    }

    public Range getRange() {
        return range;
    }

    public MaxWeightCapacity getMaxWeightCapacity() {
        return maxWeightCapacity;
    }

    public MaxVolumeCapacity getMaxVolumeCapacity() {
        return maxVolumeCapacity;
    }

    public Model getModel() {
        return model;
    }

    public BriefDescription getBriefDescription() {
        return briefDescription;
    }

    public AGVPosition getPosition() {
        return position;
    }

    public AGVStatus getAgvStatus() {
        return agvStatus;
    }

    public List<AGVTask> getAgvTask(){ return agvTask; }

    private AGVTask getTaskByDescription(String description){
        for (AGVTask task : agvTask){
            if (task.getDescription().equals(description)) return task;
        }

        return null;
    }

    public void addOrdersToATask(String description, ProductOrder order) throws Exception {

        AGVTask agvTask = getTaskByDescription(description);

        if (agvTask != null){
            if ((agvTask.getTotalWeight() + order.getOrderWeight().getWeight()) < maxWeightCapacity.getMaxWeightCapacity() && (agvTask.getTotalVolume() + order.getOrderVolume().getVolume()) < maxVolumeCapacity.getMaxVolumeCapacity()){
                agvTask.addMoreOrders(order);
            }
        }
        else{
            throw new Exception("This order exceeds the limits supported by that AGV");
        }

    }

    public void changeStatusOfAGVForOccupied(){
        this.agvStatus = AGVStatus.OCCUPIED_SERVING;
    }

    public void changeStatusOfAGVForFree(){
        this.agvStatus = AGVStatus.FREE;
    }

    public void changeStatusOfAGVForCharging(){
        this.agvStatus = AGVStatus.CHARGING;
    }

    public void changeStatusOfAGVForMaintenance(){
        this.agvStatus = AGVStatus.MAINTENANCE;
    }

    public void changeStatusOfAGVForGivenOrder(){
        this.agvStatus = AGVStatus.GIVEN_ORDER;
    }

    public void assignATaskForAGV(AGVTask task){
        this.agvTask.add(task);
    }

    public void changeAGVId(AGVId id){this.agvId=id;}

}