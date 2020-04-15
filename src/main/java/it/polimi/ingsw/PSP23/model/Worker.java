package it.polimi.ingsw.PSP23.model;
/**
* Worker class
*/
public class Worker {
    private Cell cell;
    Color color;

    /**
    *   Costructor used when the worker are placed on the map
    *   The color of the worker defines who it belogs to
    *   @param c the cell in which the worker must be positioned
    *   @param color the worker color
    */
    public Worker(Cell c, Color color) {
        this.cell = c;
        c.setWorker(this);
        this.color = color;
    }

    /**
    *   @param color worker color to set
    */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
    *   @return worker color
    */
    public Color getColor() {
        return color;
    }

    /**
    *   Call the function used to return position X on the map
    *   @return the X position on the map
    */
    public int getPosX() {
        return cell.getX();
    }

    /**
    *   Call the function used to return position Y on the map
    *   @return Y position on the map
    */
    public int getPosY() {
        return cell.getY();
    }

    /**
    *   Call the function used to return position Z on the map
    *   @return Z position on the map
    */
    public int getPosZ() {
        return cell.height();
    }

    /**
    *   @return cell object
    */
    public Cell getCell() {
        return this.cell;
    }

    /**
    *   The worker is removed from the current cell and placed into the given cell
    *   @param c the cell in which the worker need to be placed
    */
    public void moveWorker(Cell c){
        cell.fireWorker();
        cell = c;
        cell.setWorker(this);
    }

}
