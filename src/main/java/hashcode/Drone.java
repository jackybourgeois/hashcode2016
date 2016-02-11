package hashcode;

import java.util.HashMap;

/**
 * @author Jacky Bourgeois
 * @version %I%, %G%
 */
public class Drone {

    private int id;
    private int x;
    private int y;
    private Simulation sim;
    private HashMap<Integer, Integer> productQu;

    public Drone(int id, int x, int y, Simulation sim) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.sim = sim;
    }

    public int euclideanVal(int xa, int ya, int xb, int yb) {
        double tmpX = Math.pow(xa - xb, 2);
        double tmpY = Math.pow(ya - yb, 2);
        int turns = (int) Math.ceil(Math.sqrt(tmpX + tmpY));
        sim.reduceTime(turns);
        return turns;
    }

    public int getId() {
        return id;
    }

    public void load(Warehouse wh, int locX, int locY) {
        checkAndGo(locX, locY);
    }

    private void checkAndGo(int locX, int locY) {
        if (x!=locX||y!=locY) {
            euclideanVal(x,y,locX,locY);
        }
    }

    public void deliver() {

    }

    public void unload() {

    }

    public void droneWait() {

    }

    public String toString() {
        return "drone " + id + " at " + x + ";" + y;
    }

}
