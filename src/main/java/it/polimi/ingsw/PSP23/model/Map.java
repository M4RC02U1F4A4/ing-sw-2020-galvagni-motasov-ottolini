package it.polimi.ingsw.PSP23.model;

/**
*   Map class
*/
public class Map {
    private Cell cells[][];

    /**
    *   Constructor that create the 5x5 map,
    *   every position in the map is set with a cell
    */
    public Map(){
        cells=new Cell[5][5];
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                cells[i][j]=new Cell(i,j);
            }
        }
    }

    /**
    *   Debug
    *   Print the map with the cell status
    */
    public void printStatus()
    {
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++) {
                System.out.println("Cella["+i+"]["+j+"]\n"+cells[i][j].toString());
            }
        }
    }
}
