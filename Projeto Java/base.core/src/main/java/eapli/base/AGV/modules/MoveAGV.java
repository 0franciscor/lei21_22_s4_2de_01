package eapli.base.AGV.modules;

import eapli.base.AGV.domain.AGV;
import eapli.base.infrastructure.persistence.PersistenceContext;

import java.util.List;

public class MoveAGV extends Thread {

    private final AGV agv;

    private final WarehouseMovement whMovement;

    private int x;

    private int y;

    public MoveAGV(final AGV agv, final WarehouseMovement whMovement) {
        this.agv = agv;
        this.whMovement = whMovement;
    }

    public void run() {
        agv.activateSensors(this);
        moveAGV(x, y);
    }

    private void moveAGV(final int desiredX, final int desiredY) {
        var array = agv.getPosition().getAgvPosition().split(",");
        var x = Integer.parseInt(array[0]);
        var y = Integer.parseInt(array[1]);
        var coordinate = WarehouseMovement.minDistance(whMovement.getGrid(), x, y, desiredX, desiredY);

        if(coordinate == null) {
            System.out.println("There is no available Path");
            return;
        }

        if (x == desiredX && y == desiredY) {
            System.out.println("The AGV is already placed at the desired Location");
            return;
        }

        int control;
        List<Coordinate> pathList = WarehouseMovement.backTrackPath(coordinate);
        for (var path : pathList) {
            array = agv.getPosition().getAgvPosition().split(",");
            x = Integer.parseInt(array[0]);
            y = Integer.parseInt(array[1]);
            updateGrid(path, x, y);
            updateAGV(path);

            control = getAction();

            whMovement.printMatrix();

            if(control != -1) {
                try {
                    sleep(control);
                } catch (InterruptedException e) {
                    System.out.println("There was a problem regulating the AGV speed.");
                }
            } else{
                moveAGV(desiredX, desiredY);
                break;
            }
        }
    }

    protected void setCoordinates(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    private void updateAGV(final Coordinate path) {
        agv.updateAGVPosition(path.getRow() + "," + path.getCol());
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

    private int getAction(){
        int best = -1, control = 0;
        for (var sensor : agv.getSensors()){
            control = sensor.getControl();
            if(best < control)
                best = control;
        }

        if(control == 0)
            return 1000;
        else if(control == 2)
            return 2000;
        else
            return -1;
    }
}
