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

    /**
     * method used to draw the map
     */

    public void drawMap()
    {
        System.out.println("+----------------+----------------+----------------+----------------+----------------+");
        String tmp="";
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                tmp=tmp+"| CELL N."+i+j+"      ";
            }
            tmp=tmp+"|";
            System.out.println(tmp);
            tmp="";
            for(int j=0;j<5;j++){
                if(getCell(i,j).levelStatus(3)==Status.BUILT){
                    tmp=tmp+"| L3:BUILT       ";
                }
                else if(getCell(i,j).levelStatus(3)==Status.FREE){
                    tmp=tmp+"| L3:FREE        ";
                }
                else if(getCell(i,j).levelStatus(3)==Status.CUPOLA){
                    tmp=tmp+"| L3:CUPOLA      ";
                }
            }
            tmp=tmp+"|";
            System.out.println(tmp);
            tmp="";
            for(int j=0;j<5;j++){
                if(getCell(i,j).levelStatus(2)==Status.BUILT){
                    tmp=tmp+"| L2:BUILT       ";
                }
                else if(getCell(i,j).levelStatus(2)==Status.FREE){
                    tmp=tmp+"| L2:FREE        ";
                }
                else if(getCell(i,j).levelStatus(2)==Status.CUPOLA){
                    tmp=tmp+"| L2:CUPOLA       ";
                }
            }
            tmp=tmp+"|";
            System.out.println(tmp);
            tmp="";

            for(int j=0;j<5;j++){
                if(getCell(i,j).levelStatus(1)==Status.BUILT){
                    tmp=tmp+"| L1:BUILT       ";
                }
                else if(getCell(i,j).levelStatus(1)==Status.FREE){
                    tmp=tmp+"| L1:FREE        ";
                }
                else if(getCell(i,j).levelStatus(1)==Status.CUPOLA){
                    tmp=tmp+"| L1:CUPOLA       ";
                }
            }
            tmp=tmp+"|";
            System.out.println(tmp);
            tmp="";
            for(int j=0;j<5;j++){
                if(getCell(i,j).levelStatus(0)==Status.BUILT){
                    tmp=tmp+"| L0:BUILT       ";
                }
                else if(getCell(i,j).levelStatus(0)==Status.FREE){
                    tmp=tmp+"| L0:FREE        ";
                }
                else if(getCell(i,j).levelStatus(0)==Status.CUPOLA){
                    tmp=tmp+"| L0:CUPOLA       ";
                }
            }

            tmp=tmp+"|";
            System.out.println(tmp);
            tmp="";
            for(int j=0;j<5;j++){
                if(getCell(i,j).isOccupied()){
                    tmp=tmp+"| OCCUPATA DA W0  ";
                }
                else{
                    tmp=tmp+"| LIBERA         ";
                }
            }
            tmp=tmp+"|";
            System.out.println(tmp);
            tmp="";
            System.out.println("+----------------+----------------+----------------+----------------+----------------+");
        }
    }



}