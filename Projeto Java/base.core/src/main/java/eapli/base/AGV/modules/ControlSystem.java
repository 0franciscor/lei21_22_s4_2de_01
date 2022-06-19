package eapli.base.AGV.modules;

import eapli.base.AGV.domain.AGV;

public class ControlSystem extends Thread{

    private final Thread csThread;

    private final MoveAGV moveAGV;

    private final AGV agv;

    public ControlSystem(final AGV agv){
        csThread = new Thread(this);
        this.agv = agv;
        this.moveAGV = new MoveAGV(agv, WarehouseMovement.getWarehouseMovement(), this);
    }

    public Thread getControlSystemThread(){
        return csThread;
    }

    @Override
    public void run(){
        var taskList = agv.getAgvTask();

        synchronized (this) {
            for (var task : taskList) {
                if (task.getStatus() == 1) {
                    var array = task.getLocation().split(",");
                    moveAGV.setCoordinates(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
                    moveAGV.start();
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        System.out.println("There was an error when refreshing the Control System.");
                    }
                }
            }
        }
    }

    public void disableLock(){
        synchronized (this) {
            notify();
        }
    }
}