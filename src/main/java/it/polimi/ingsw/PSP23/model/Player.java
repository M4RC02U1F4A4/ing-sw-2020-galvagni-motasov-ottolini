package it.polimi.ingsw.PSP23.model;

/**
*   Player class
*/
public class Player {
    private String name;
    private String ipAddress;
    private Worker[] workers;
    private God god;
    private Color color;
    private int playerNumber;

    public Player(String name, String ipAddress) {
        this.name=name;
        this.ipAddress=ipAddress;
        workers=new Worker[2];
        color=null;
        god = null;
        workers[0] = null;
        workers[1] = null;
    }

    /**
    *   @return the name of the player
    */
    public String getName() {
        return name;
    }

    /**
    *   @param name name of the player to set
    */
    public void setName(String name) {
        this.name = name;
    }

    /**
    *   @return the IP address of the player
    */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
    *   @param ipAddress IP address of the player to set
    */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
    *   @return god of the player
    */
    public God getGod() {
        return god;
    }

    /**
    *   @param god god to set for the player
    */
    public void setGod(God god) {
        this.god = god;
    }


    /**
     *   @return player worker list
     */
    public Worker[] getWorkers() {
        return workers;
    }

    /**
    *   Check if there is some worker available and return it
    *   @param num number of worker that the player owns
    *   @return return worker if available, or return null
    */
    public Worker getWorkerByNumber(int num) {
        if(num==0 || num==1) return workers[num];
        else return null;
    }

    /**
     * A function to rule them all, a function to set them all
     * @param c the cell where the worker will be placed
     * @return  0 if worker 0 is created,
     *          1 if worker 1 is created,
     *         -1 if all worker are already initialized
     */
    public int placeWorker(Cell c) {
        if (null == workers[0]) {
            workers[0] = new Worker(c, color);
            return 0;
        }
        else if (null == workers[1]) {
            workers[1] = new Worker(c, color);
            return 1;
        }
        else
            return -1;
    }

    /**
     * Getter for the color
     * @return a color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Set the Color for the player
     * @param color the color we want to set
     */
    public void setColor(Color color) {
        this.color = color;
        //workers[0].setColor(color);
        //workers[1].setColor(color);
    }

    /**
     * Gets the playernumber, used for managing the turns
     * @return the player's number
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * sets the player's number
     * @param playerNumber the player's number
     */
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    // DEBUG ONLY! use placeWorker instead TODO remove me
    public void setWorkers(Worker[] workers) {
        this.workers = workers;
    }

    // DEBUG ONLY! use placeWorker instead TODO remove me
    public void setWorkerByNumber(Worker w,int num){
        if(num==0 || num==1)workers[num]=w;
        else System.out.println("error");
    }
}
