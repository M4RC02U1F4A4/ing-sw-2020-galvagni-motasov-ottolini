package it.polimi.ingsw.PSP23.model;

public class God {
    private String name;


    public void move(Worker w, int x, int y, int z){
        if(x>=0 && x<5 && y>=0 && y<5){//verificare anche la coordinata z
            w.setPosX(x);
            w.setPosY(y);
            w.setPosZ(z);
        }

    }


    public void build(Cell c){

    }


    //public void checkWin();

}