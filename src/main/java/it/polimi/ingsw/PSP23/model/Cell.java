package it.polimi.ingsw.PSP23.model;

import java.io.Serializable;

import static it.polimi.ingsw.PSP23.model.Status.*;
/**
*   Cell class
*/
public class Cell implements Serializable {
    private Status[] levels;
    Worker worker;
    private int X;
    private int Y;

    /**
    *   Costructor that set Cell with 4 high levels,
    *   every level status is set to free and worker status is ste to NULL
    *   @param x X position on the map
    *   @param y Y position on the map
    */
    public Cell(int x, int y){
        levels=new Status[4];
        for (int i=0; i<levels.length; i++) {
            levels[i] = FREE;
        }
        worker=null;
        X = x;
        Y = y;
    }

    /**
     * Cell cos
     */
    public Cell(){
        levels=new Status[4];
        for (int i=0;i<levels.length;i++) {
            levels[i] = FREE;
        }
        worker=null;
    }

    /**
     * Clone a single cell
     * @return cloned cell
     */
    public Cell clone(){
        Cell f=new Cell();
        f.X=this.X;
        f.Y=this.Y;
        f.worker=this.worker;
        for (int i=0;i<levels.length;i++) {
            f.levels[i] = this.levels[i];
        }
        return f;
    }

    /**
    *   @return cell object
    */
    public Worker getWorker(){
        return this.worker;
    }

    /**
    *   @param w worker that the player want to place
    */
    public void setWorker(Worker w){
        worker = w;
    }

    /**
    *   Free the cell from the worker
    */
    public void fireWorker(Worker caller) {
        if (caller == worker)
            worker = null;
    }

    /**
    *   @return X position
    */
    public int getX () {
        return X;
    }

    /**
    *   @return Y position
    */
    public int getY () {
        return Y;
    }

    /**
    *   Check if the worker is adjacent the cell
    *   and if the difference between worker height and cell height is <= 1
    *   @param w worker
    *   @return true if the condition is verified, false other otherwise
    */
    public boolean isNear (Worker w, boolean height) {
        if ((this.X >= w.getPosX() - 1) && (this.X <= w.getPosX() + 1)) {
            if ((this.Y >= w.getPosY() - 1) && (this.Y <= w.getPosY() + 1)) {
                if (height)
                    return (this.height() <= w.getPosZ() + 1);
                else
                    return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
    *   Check if there is è worker in the cell
    *   @return true if the condition is verified, false otherwise
    */
    public boolean isOccupied(){
        return worker != null;
    }

    /**
    *   Translates the state of the cell by returning its height
    *   @return the height of the cell, -1 if there are some other condition
    */
    public int height(){
        int height=0;
        for(int i=0;i<4;i++){
            switch (levels[i]) {
                case FREE:
                    break;
                case BUILT:
                    height++;
                    break;
                case CUPOLA:
                    return height + 1;
                case NOT_AVAILABLE:
                    return -1;
            }
        }
        return height;
    }

    /**
     * Return the status of the given level
     * @param height height of the status that need to be returned
     * @return Status of the given level
     */
    public Status levelStatus(int height){
        if(height < 4 && height >= 0){
            switch (levels[height]){
                case FREE:
                    return FREE;
                case BUILT:
                    return BUILT;
                case CUPOLA:
                    return CUPOLA;
                case NOT_AVAILABLE:
                    return NOT_AVAILABLE;
            }
        }
      return NOT_AVAILABLE;
    }

    /**
    *   Search for a FREE level, when it is found build a new BUILD level,
    *   if the level already contains a CUPOLA or is the last one (level 4), build a CUPOLA
    *   @param b level status
    */
    public int build (Status b) {
        int i = 0;
        while (FREE != levels[i] && i < 3) {
            i++;
        };
        if (CUPOLA == b || 3 == i) {
            levels[i] = CUPOLA;
        }
        else {
            levels [i] = BUILT;
        }
        return i;
    }

    /**
    *   Return a string made in this way
    *   livello <height> <STATUS>
    *   @return string livello <height> <STATUS>
    */
    public String toString(){
        String ret="";
        for(int i=0;i<4;i++){
            ret=ret+"livello "+i+": ";
            switch (levels[i]){
                case FREE:ret=ret+"FREE";break;
                case BUILT:ret=ret+"BUILT";break;
                case CUPOLA:ret=ret+"CUPOLA";break;
                case NOT_AVAILABLE:ret=ret+"NOT AVAILABLE";break;
            }
            ret=ret+"\n";
        }
        return ret+"OCCUPATA: "+isOccupied();
    }

    /**
     * Only used for testing purpose. DO NOT USE!
     * @param x x
     * @param y y
     */
    public void setCoord (int x, int y) {
        this.X = x;
        this.Y = y;
    }
}
