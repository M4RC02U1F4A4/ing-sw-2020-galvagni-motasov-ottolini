package it.polimi.ingsw.PSP23.model;

/**
*   Player class
*/
public class Player {
    private String name;
    private String ipAddress;
    private Worker workers[];
    private God god;

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
    *   @return player worker list
    */
    public Worker[] getWorkers() {
        return workers;
    }

    /**
    *   @param workers list of worker to set for that player
    */
    public void setWorkers(Worker[] workers) {
        this.workers = workers;
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
    *   Check if there is some worker available and return it
    *   @param num number of worker that the player owns
    *   @return return worker if available, or return null
    */
    public Worker getWorkerByNumber(int num){
        if(num==0 || num==1) return workers[num];
        else return null;
    }

    /**
    *   Save the player's worker in the specified spot on the list
    *   @param w worker
    *   @param num number where the worker need to be placed
    */
    public void setWorkerByNumber(Worker w,int num){
        if(num==0 || num==1)workers[num]=w;
        else System.out.println("error");
    }
}
