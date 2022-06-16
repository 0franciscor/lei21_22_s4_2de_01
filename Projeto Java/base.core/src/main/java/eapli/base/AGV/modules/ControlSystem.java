package eapli.base.AGV.modules;

import eapli.base.AGV.domain.AGV;

public class ControlSystem extends Thread{

    private final Thread csThread;

    private final MoveAGV moveAGV;

    private final BMS batteryLevelControl;

    public ControlSystem(final AGV agv){
        csThread = new Thread(this);
        this.moveAGV = new MoveAGV(agv, WarehouseMovement.getWarehouseMovement());
        this.batteryLevelControl = new BMS(agv);
    }

    public Thread getControlSystemThread(){
        return csThread;
    }

    @Override
    public synchronized void run(){
        moveAGV.setCoordinates(17,19);
        moveAGV.start();
        batteryLevelControl.start();

        try {
            this.wait(2000); // regula a frequência de atualização do Control System
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //private
}