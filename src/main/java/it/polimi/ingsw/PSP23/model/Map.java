package it.polimi.ingsw.PSP23.model;

public class Map {
    private Cell cells[][];

    public Map(){
        cells=new Cell[5][5];
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                cells[i][j]=new Cell();
            }
        }
    }

    //metodo di debug
    public void check()
    {
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++) {
                System.out.println("Cella["+i+"]["+j+"]\n"+cells[i][j].toString());
            }
        }
    }
}
