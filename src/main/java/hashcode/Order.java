package hashcode;

/**
 * @author Jacky Bourgeois
 * @version %I%, %G%
 */
public class Order {

    private int id;
    private int row;
    private int col;
    private int[] products;

    public Order(int id, int row, int col) {
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

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int[] getProducts() {
        return products;
    }

    public void setProducts(int[] products) {
        this.products = products;
    }


    public String toString() {
        String str = "order " + id;
        str += ", location: " + row + ";" + col;
        str += ", products: ";
        for (int i=0;i<products.length;i++) {
            str += products[i] + ",";
        }
        return str;
    }
}
