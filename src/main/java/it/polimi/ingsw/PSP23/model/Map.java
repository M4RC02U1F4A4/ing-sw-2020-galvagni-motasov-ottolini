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
     * Clone the map
     * @return cloned map
     */
    public final Map clone(){
        final Map ret=new Map();
        for(int i=0; i<5;i++){
            ret.cells[i]=cells[i].clone();
        }
        return ret;
    }


    /**
     * Return the cell of the map
     * @param x X position of the cell
     * @param y Y position of the cell
     * @return cell of given coordinates
     */
    public Cell getCell(int x, int y){
        if (x>=0 && x<5 && y>=0 && y<5) {
            return cells[x][y];
        }
        else return null;
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
