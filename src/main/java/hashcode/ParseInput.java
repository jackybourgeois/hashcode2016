package hashcode;

import java.io.*;
import java.util.HashMap;

/**
 * @author Jacky Bourgeois
 * @version %I%, %G%
 */
public class ParseInput {

    public static void main(String[] args) {
        ParseInput parseInput = new ParseInput();
        parseInput.parse("busy_day.in");
    }

    public void parse(String fileName) {
        File file = new File(fileName);
        int lineId = 0;

        Simulation simulation = null;
        int nbWarehouses = 0;
        int idWarehouse = -1;
        int nbOrder = -1;
        int idOrder = 0;
        Warehouse currentWarehouse = null;
        Order currentOrder = null;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (String line; (line = br.readLine()) != null; ) {
                if (lineId == 0) {
                    simulation = buildSimulation(line);
                } else if (lineId == 2) {
                    if (simulation != null) {
                        simulation.setProductMap(buildProductMap(line));
                    }
                } else if (lineId == 3) {
                    nbWarehouses = Integer.valueOf(line);
                    idWarehouse = 0;
                } else if (idWarehouse != -1 && idWarehouse < nbWarehouses) {
                    if (currentWarehouse == null) {
                        String[] locationLine = line.split(" ");
                        currentWarehouse = new Warehouse(idWarehouse,
                                Integer.valueOf(locationLine[0]),
                                Integer.valueOf(locationLine[1]));
                    } else {
                        currentWarehouse.setProductQu(buildWarehouseAvailability(line));
                        if (simulation != null) {
                            simulation.getWarehouseHashMap().put(idWarehouse, currentWarehouse);
                        }
                        idWarehouse++;
                        currentWarehouse = null;
                    }
                } else if (lineId == nbWarehouses*2 + 4) {
                    nbOrder = Integer.valueOf(line);
                } else if (nbOrder != -1 && idOrder < nbOrder) {
                    if (currentOrder == null) {
                        System.out.println("order " + idOrder);
                        String[] locationLine = line.split(" ");
                        currentOrder = new Order(idOrder,
                                Integer.valueOf(locationLine[0]),
                                Integer.valueOf(locationLine[1]));
                    } else if (currentOrder.getProducts()==null) {
                        currentOrder.setProducts(new int[Integer.valueOf(line)]);
                    } else {
                        int i = 0;
                        for (String str : line.split(" ")) {
                            currentOrder.getProducts()[i] = Integer.valueOf(str);
                            i++;
                        }

                        if (simulation != null) {
                            simulation.getOrderMap().put(currentOrder.getId(), currentOrder);
                        }
                        idOrder++;
                        currentOrder = null;
                    }
                }
                lineId++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Warehouse firstWH = simulation.getWarehouseHashMap().get(0);
        HashMap<Integer, Drone> droneMap = new HashMap<>();
        for (int i=0;i<simulation.getNbDrones();i++) {
            droneMap.put(i, new Drone(i, firstWH.getRow(), firstWH.getCol(), simulation));
        }
        simulation.setDroneMap(droneMap);

        showParamSimulation(simulation);

    }

    private void showParamSimulation(Simulation sim) {

        System.out.println("cols: " + sim.getNbCols());
        System.out.println("rows: " + sim.getNbRows());
        System.out.println("mar load: " + sim.getMaxLoadDrone());
        System.out.println("deadline: " + sim.getDeadline());

        System.out.println("product map: " + sim.getProductMap());

        for (Warehouse wh : sim.getWarehouseHashMap().values()) {
            System.out.println(wh);
        }

        System.out.println("orders: " + sim.getOrderMap().size());

        for (Order order : sim.getOrderMap().values()) {
            System.out.println(order);
        }

        System.out.println("drones: ");

        for (Drone drone : sim.getDroneMap().values()) {
            System.out.println(drone);
        }
    }

    private HashMap<Integer, Integer> buildProductMap(String line) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int id = 0;
        for (String str : line.split(" ")) {
            map.put(id, Integer.valueOf(str));
            id++;
        }
        return map;
    }

    public Simulation buildSimulation(String line) {
        String[] elems = line.split(" ");
        return new Simulation(Integer.valueOf(elems[0]),
                Integer.valueOf(elems[1]),
                Integer.valueOf(elems[2]),
                Long.valueOf(elems[3]),
                Integer.valueOf(elems[4]));
    }

    public HashMap<Integer, Integer> buildWarehouseAvailability(String line) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int id = 0;
        for (String str : line.split(" ")) {
            map.put(id, Integer.valueOf(str));
            id++;
        }
        return map;
    }


}
