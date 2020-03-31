package it.polimi.ingsw.PSP23.model;

public class Cell {
    private Status levels[];
    Worker worker;

    public Cell(){
        levels=new Status[4];
        for (int i=0;i<levels.length;i++) {
            levels[i] = Status.FREE;
        }
        worker=null;
    }

    public Worker getWorker(){
        return this.worker;
    }

    public void setWorker(Worker w){
        worker=w;
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
