package it.polimi.ingsw.PSP23.model;

public class Worker {
    private Cell cell;
    Color color;

    //costruttore da usare quando si collocano i giocatori sulla mappa
    public Worker(Cell c, Color color) {
        this.cell = c;
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getPosX() {
        return cell.getX();
    }

    public int getPosY() {
        return cell.getY();
    }

    public int getPosZ() {
        return cell.height();
    }

    public Cell getCell() {
        return this.cell;
    }

    public void moveWorker(Cell c){
        cell.fireWorker();
        cell = c;
        cell.setWorker(this);
    }

}
