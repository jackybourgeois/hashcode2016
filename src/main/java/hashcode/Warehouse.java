package hashcode;

import java.util.HashMap;

/**
 * @author Jacky Bourgeois
 * @version %I%, %G%
 */
public class Warehouse {

    private int id;
    private int row;
    private int col;
    private HashMap<Integer, Integer> productQu;

    public Warehouse(int id, int row, int col) {
        this.id = id;
        this.row = row;
        this.col = col;
    }

    public int getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public HashMap<Integer, Integer> getProductQu() {
        return productQu;
    }

    public void setProductQu(HashMap<Integer, Integer> productQu) {
        this.productQu = productQu;
    }

    public String toString() {
        String str = "warehouse " + id;
        str += ", location: " + row + ";" + col;
        str += ", products: " + productQu;
        return str;
    }
}
