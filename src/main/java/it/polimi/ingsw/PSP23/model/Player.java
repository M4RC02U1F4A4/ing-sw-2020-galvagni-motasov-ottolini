package it.polimi.ingsw.PSP23.model;

public class Player {
    private String name;
    private String ipAddress;
    private Worker workers[];
    private God god;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Worker[] getWorkers() {
        return workers;
    }

    public void setWorkers(Worker[] workers) {
        this.workers = workers;
    }

    public God getGod() {
        return god;
    }

    public void setGod(God god) {
        this.god = god;
    }

    public Worker getWorkerByNumber(int num){
        if(num==0 || num==1) return workers[num];
        else return null;
    }

    public void setWorkerByNumber(Worker w,int num){
        if(num==0 || num==1)workers[num]=w;
        else System.out.println("error");
    }
}
