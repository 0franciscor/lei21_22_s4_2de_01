package eapli.base.AGV.modules;

import eapli.base.AGV.domain.AGV;
import eapli.base.infrastructure.persistence.PersistenceContext;

import java.util.List;

public class MoveAGV extends Thread {

    private final AGV agv;

    private final WarehouseMovement whMovement;

    private static final int ACCEPTED_LEVEL_BATTERY = 25;

    private int desiredX;

    private int desiredY;

    private int speed;

    public MoveAGV(final AGV agv, final WarehouseMovement whMovement) {
        this.agv = agv;
        this.whMovement = whMovement;
        this.speed = -1;
    }

    public void run() {
        agv.activateSensors();
        while(speed == -1)
            moveAGV();
        agv.deactivateSensors();
    }

    private void moveAGV() {

        var array = agv.getPosition().getAgvPosition().split(",");
        var x = Integer.parseInt(array[0]);
        var y = Integer.parseInt(array[1]);
        var coordinate = WarehouseMovement.minDistance(whMovement.getGrid(), x, y, desiredX, desiredY);

        if(coordinate == null) {
            System.out.println("There is no available Path");
            speed = 0;
            return;
        }

        if (!checkBaterry()){
            System.out.println("There is no sufficient battery to perform the trip");
            return;
        }

        if (x == desiredX && y == desiredY) {
            System.out.println("The AGV is already placed at the desired Location");
            speed = 0;
            return;
        }


        List<Coordinate> pathList = WarehouseMovement.backTrackPath(coordinate);
        for (var path : pathList) {
            array = agv.getPosition().getAgvPosition().split(",");
            x = Integer.parseInt(array[0]);
            y = Integer.parseInt(array[1]);
            updateGrid(path, x, y);

            if (!checkBaterry()){
                int coordinateX = agv.getAgvDock().getBegin().getBeginLSquare();
                int coordinateY = agv.getAgvDock().getBegin().getBeginWSquare();

                coordinate = WarehouseMovement.minDistance(whMovement.getGrid(), x, y, coordinateX, coordinateY);
                pathList = WarehouseMovement.backTrackPath(coordinate);

                for (var pathAux : pathList){
                    array = agv.getPosition().getAgvPosition().split(",");
                    x = Integer.parseInt(array[0]);
                    y = Integer.parseInt(array[1]);

                    updateGrid(pathAux, x, y);

                    updateAGV(pathAux);
                    speed = getAction();
                    changeSpeed(speed/1000);

                    whMovement.printMatrix();

                    if(speed != -1) {
                        try {
                            sleep(speed);
                        } catch (InterruptedException e) {
                            System.out.println("There was a problem regulating the AGV speed.");
                        }
                    } else{
                        moveAGV();
                        break;
                    }
                }
                changeAGVStatus();
                chargeAGV();

                break;

            }

            updateAGV(path);

            speed = getAction();
            changeSpeed(speed/1000);

            whMovement.printMatrix();

            if(path.getRow() == desiredX && path.getCol() == desiredY) {
                speed = 0;
                changeSpeed(speed);
                return;
            }

            if(speed != -1) {
                try {
                    sleep(speed);
                } catch (InterruptedException e) {
                    System.out.println("There was a problem regulating the AGV speed.");
                }
            } else{
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("There was a problem regulating the AGV speed.");
                }
                break; // joga com return e valor do speed para indicar o fim e fica praticamente feito
            }
        }
    }

    protected void setCoordinates(final int desiredX, final int desiredY) {
        this.desiredX = desiredX;
        this.desiredY = desiredY;
    }

    private void updateAGV(final Coordinate path) {
        agv.updateAGVPosition(path.getRow() + "," + path.getCol());
        agv.getBattery().decresyBatteryLevel(1);
        updateDatabase();
    }

    private void updateGrid(final Coordinate path, final int x, final int y) {
        whMovement.updateGrid(x, y, path.getRow(), path.getCol());
    }

    private void updateDatabase() {
        var repo = PersistenceContext.repositories().newTransactionalContext();
        repo.beginTransaction();
        PersistenceContext.repositories().agv().save(agv);
        repo.commit();
    }

    private void changeAGVStatus(){
        agv.changeStatusOfAGVForCharging();
        updateDatabase();
    }

    private void chargeAGV(){
        agv.getBattery().chargeBattery();
        updateDatabase();

    }

    private int getAction(){
        int best = -1, control;

        var sensorList = agv.getSensors();

        for (var sensor : sensorList){
            control = sensor.getControl();
            if(best < control)
                best = control;
            sensor.disableLock();
        }

        if(best == 0)
            return 1000;
        else if(best == 1)
            return 2000;
        else
            return -1;
    }

    private boolean checkBaterry(){
        return agv.getBattery().getBatteryLevel() > ACCEPTED_LEVEL_BATTERY;
    }

    private void changeSpeed(int speed){
        agv.setSpeed(speed);
        updateDatabase();
    }
}
