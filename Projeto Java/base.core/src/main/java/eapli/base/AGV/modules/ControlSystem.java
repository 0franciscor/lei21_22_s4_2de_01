package eapli.base.AGV.modules;

import eapli.base.AGV.domain.AGV;

public class ControlSystem extends Thread{

    private final Thread csThread;

    private final MoveAGV moveAGV;


    public ControlSystem(final AGV agv){
        csThread = new Thread(this);
        this.moveAGV = new MoveAGV(agv, WarehouseMovement.getWarehouseMovement());
    }

    public Thread getControlSystemThread(){
        return csThread;
    }

    @Override
    public synchronized void run(){

        moveAGV.setCoordinates(17,19);

        moveAGV.setCoordinates(4,0);

        moveAGV.start();

        try {
            this.wait(1000); // regula a frequência de atualização do Control System
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}