package it.polimi.ingsw.PSP23.model;

import static it.polimi.ingsw.PSP23.model.Status.*;

public class Cell {
    private Status levels[];
    Worker worker;
    private int X;
    private int Y;

    public Cell(int x, int y){
        levels=new Status[4];
        for (int i=0;i<levels.length;i++) {
            levels[i] = FREE;
        }
        worker=null;
        X = x;
        Y = y;
    }

    public Worker getWorker(){
        return this.worker;
    }

    public void setWorker(Worker w){
        worker=w;
    }

    public void fireWorker() {
        worker = null;
    }

    public int getX () {
        return X;
    }

    public int getY () {
        return Y;
    }

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

    public boolean isOccupied(){
        if(worker==null)
            return false;
        else return true;
    }

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
