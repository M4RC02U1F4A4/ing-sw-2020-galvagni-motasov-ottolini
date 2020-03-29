package it.polimi.ingsw.PSP23.model;

public class Cell {
    private Status levels[];

    public Cell(){
        levels=new Status[4];
        for (int i=0;i<levels.length;i++) {
            levels[i] = Status.FREE;
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
