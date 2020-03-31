package it.polimi.ingsw.PSP23.model;

public class Worker {
    int posX;
    int posY;
    int posZ;
    Color color;

    //costruttore da usare quando si collocano i giocatori sulla mappa
    public Worker(int posX, int posY, int posZ, Color color) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.color = color;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setPosZ(int posZ) {
        this.posZ = posZ;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getPosZ() {
        return posZ;
    }

    public Color getColor() {
        return color;
    }

    public void moveWorker(int x, int y, int z){

    }

}
