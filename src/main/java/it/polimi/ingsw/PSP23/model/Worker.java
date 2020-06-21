package it.polimi.ingsw.PSP23.model;

import java.io.Serializable;

/**
* Worker class
*/
public class Worker implements Serializable {
    private Cell cell;
    Color color;
    int workerNumber = -1;

    /**
    *   Costructor used when the worker are placed on the map
    *   The color of the worker defines who it belogs to
    *   @param c the cell in which the worker must be positioned
    *   @param color the worker color
    *   @param number the worker number (0 or 1)
    */
    public Worker(Cell c, Color color, int number) {
        this.cell = c;
        this.workerNumber = number;
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
        cell.fireWorker(this);
        cell = c;
        c.setWorker(this);
    }

    /**
     * Method used to set the worker's number, in order to indentify a worker when the map is printed
     * @param n the number to set
     */
    public void setWorkerNumber(int n){
        workerNumber=n;
    }

    /**
     * Method used to get the Workernumber, in order to indentify a worker when the map is printed
     * @return workerNumber
     */
    public int getWorkerNumber(){return workerNumber;}

}
