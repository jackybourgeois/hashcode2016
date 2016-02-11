package hashcode;

import java.util.HashMap;

/**
 * @author Jacky Bourgeois
 * @version %I%, %G%
 */
public class Simulation {

    private int nbRows;
    private int nbCols;
    private int nbDrones;
    private long deadline;
    private int maxLoadDrone;
    private HashMap<Integer, Integer> productMap;
    private HashMap<Integer, Warehouse> warehouseHashMap;
    private HashMap<Integer, Order> orderMap;
    private HashMap<Integer, Drone> droneMap;

    public Simulation(int nbRows, int nbCols, int nbDrones, long deadline, int maxLoadDrone) {
        this.nbRows = nbRows;
        this.nbCols = nbCols;
        this.nbDrones = nbDrones;
        this.deadline = deadline;
        this.maxLoadDrone = maxLoadDrone;
        this.productMap = new HashMap<>();
        this.warehouseHashMap = new HashMap<>();
        this.orderMap = new HashMap<>();
    }

    public int getNbRows() {
        return nbRows;
    }

    public int getNbCols() {
        return nbCols;
    }

    public int getNbDrones() {
        return nbDrones;
    }

    public long getDeadline() {
        return deadline;
    }

    public int getMaxLoadDrone() {
        return maxLoadDrone;
    }

    public HashMap<Integer, Integer> getProductMap() {
        return productMap;
    }

    public void setProductMap(HashMap<Integer, Integer> productMap) {
        this.productMap = productMap;
    }

    public HashMap<Integer, Warehouse> getWarehouseHashMap() {
        return warehouseHashMap;
    }

    public void setWarehouseHashMap(HashMap<Integer, Warehouse> warehouseHashMap) {
        this.warehouseHashMap = warehouseHashMap;
    }

    public HashMap<Integer, Order> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(HashMap<Integer, Order> orderMap) {
        this.orderMap = orderMap;
    }

    public HashMap<Integer, Drone> getDroneMap() {
        return droneMap;
    }

    public void setDroneMap(HashMap<Integer, Drone> droneMap) {
        this.droneMap = droneMap;
    }


    public long reduceTime(int turns) {
        if (deadline>turns) {
            deadline -= turns;
            return deadline;
        }
        return turns - deadline;
    }
}
