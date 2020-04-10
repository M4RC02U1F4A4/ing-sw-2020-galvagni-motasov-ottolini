package it.polimi.ingsw.PSP23.model;

import static it.polimi.ingsw.PSP23.model.Status.*;
/**
*   Cell class
*/
public class Cell {
    private Status levels[];
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
        for (int i=0;i<levels.length;i++) {
            levels[i] = FREE;
        }
        worker=null;
        X = x;
        Y = y;
    }

    /**
    *   Return worker object
    *   @return cell object
    */
    public Worker getWorker(){
        return this.worker;
    }

    /**
    *   Set the worker into the cell
    *   @param w worker that the player want to place
    */
    public void setWorker(Worker w){
        worker=w;
    }

    /**
    *   Free the cell from the worker
    */
    public void fireWorker() {
        worker = null;
    }

    /**
    *   Return X position
    *   @return X position
    */
    public int getX () {
        return X;
    }

    /**
    *   Return Y position
    *   @return Y position
    */
    public int getY () {
        return Y;
    }

    /**
    *   Check if the worker is adjacent the cell
    *   and if the difference between worker height and cell height is <= 1
    *   @param c
    *   @param w
    *   @return true if the condition is verified, false other otherwise
    */
    public boolean isNear (Cell c, Worker w) {
        if ((X >= w.getPosX() - 1) && (X <= w.getPosX() + 1)) {
            if ((Y >= w.getPosY() - 1) && (Y <= w.getPosY() + 1)) {
                return (this.height() <= w.getPosZ() + 1);
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
        if(worker==null)
            return false;
        else return true;
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
                    return height;
                case NOT_AVAILABLE:
                    return -1;
            }
        }
        return height;
    }

    /**
    *   Search for a FREE level, when it is found build a new BUILD level,
    *   if the level already contains a CUPOLA or is the last one (level 4), build a CUPOLA
    *   @param b level status
    */
    public void build (Status b) {
        int i = 0;
        while (FREE != levels[i]) {
            i++;
        };
        if (CUPOLA == b || 4 == i) {
            levels[i] = CUPOLA;
        }
        else {
            levels [i] = BUILT;
        }
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
        return ret;
    }

}
