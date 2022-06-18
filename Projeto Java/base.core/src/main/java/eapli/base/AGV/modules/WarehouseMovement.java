package eapli.base.AGV.modules;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehouse.domain.WarehousePlant;
import eapli.base.warehouse.repositories.WarehousePlantRepository;

import java.util.*;

public class WarehouseMovement{

    private final WarehousePlantRepository warehousePlantRepository = PersistenceContext.repositories().plant();
    private static WarehouseMovement warehouseMovement;
    private int[][] grid;


    public WarehousePlant findById(String id) {
        return warehousePlantRepository.findById(id);
    }
    private WarehouseMovement(){
        WarehousePlant plant = findById("1");
        buildGrid(plant.getLength()/ plant.getSquare(), plant.getWidth()/ plant.getSquare());
    }

    public synchronized static WarehouseMovement getWarehouseMovement(){
        if(warehouseMovement == null)
            warehouseMovement = new WarehouseMovement();
        return warehouseMovement;
    }

    public int[][] getGrid(){
        return grid;
    }

    private synchronized void buildGrid(final int comprimento, final int largura){
        this.grid = new int[comprimento][largura];
        for(int i = 0; i < comprimento; i++)
            for(int j = 0; j < largura; j++)
                this.grid[i][j] = 1;
    }

    protected synchronized static Coordinate minDistance(int[][] grid, final int x, final int y, final int desiredX, final int desiredY) {
        Coordinate source = new Coordinate(x, y, 0, null);

        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(source);

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[source.getRow()][source.getCol()] = true;

        while (!queue.isEmpty()) {

            Coordinate current = queue.remove();

            if (current.getRow() == desiredX && current.getCol() == desiredY){
                return current;
            }

            // moving up
            if (isValid(current.getRow() - 1, current.getCol(), grid, visited)) {
                queue.add(new Coordinate(current.getRow() - 1, current.getCol(),
                        current.getDist() + 1, current));
                visited[current.getRow() - 1][current.getCol()] = true;
            }

            // moving down
            if (isValid(current.getRow() + 1, current.getCol(), grid, visited)) {
                queue.add(new Coordinate(current.getRow() + 1, current.getCol(),
                        current.getDist() + 1, current));
                visited[current.getRow() + 1][current.getCol()] = true;
            }

            // moving left
            if (isValid(current.getRow(), current.getCol() - 1, grid, visited)) {
                queue.add(new Coordinate(current.getRow(), current.getCol() - 1,
                        current.getDist() + 1, current));
                visited[current.getRow()][current.getCol() - 1] = true;
            }

            // moving right
            if (isValid(current.getRow(), current.getCol() + 1, grid,
                    visited)) {
                queue.add(new Coordinate(current.getRow(), current.getCol() + 1,
                        current.getDist() + 1, current));
                visited[current.getRow()][current.getCol() + 1] = true;
            }
        }
        return null;
    }

    private synchronized static boolean isValid(int x, int y, int[][] grid, boolean[][] visited) {
        boolean withinBounds = x >= 0 && y >= 0 && x < grid.length && y < grid[0].length;
        boolean notWall = false, notObstacle = false, notVisited = false;
        if(withinBounds) {
            notWall = grid[x][y] != 0;
            notObstacle = grid[x][y] != 2;
            notVisited = !visited[x][y];
        }

        return withinBounds && notWall && notObstacle && notVisited;
    }

    protected synchronized void updateGrid(final int oldX, final int oldY, final int x, final int y){
        grid[oldX][oldY] = 1;
        grid[x][y] = 2;
    }

    protected synchronized static List<Coordinate> backTrackPath(Coordinate current){
        List<Coordinate> path = new ArrayList<>();

        while(current != null){
            path.add(current);
            current = current.getParent();
        }

        Collections.reverse(path);

        return path;
    }

    protected synchronized void printMatrix(){
        String GREEN_BRIGHT = "\033[0;92m";  // GREEN
        String WHITE_BRIGHT = "\033[0;97m";  // WHITE
        String RED_BRIGHT = "\033[0;91m";    // RED

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                int num = grid[i][j];
                if(num == 2)
                    System.out.printf(GREEN_BRIGHT + "%d ", num);
                else if(num == 1)
                    System.out.printf(WHITE_BRIGHT + "%d ", num);
                else
                    System.out.printf(RED_BRIGHT + "%d ", num);
            }
            System.out.println();
        }
        System.out.println();
    }
}