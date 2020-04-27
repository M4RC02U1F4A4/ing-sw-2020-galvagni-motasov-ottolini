package it.polimi.ingsw.PSP23.server;

import java.util.Timer;
import java.util.TimerTask;

public class CustomTimer {
    Timer t;
    boolean timeIsUp;
    int connections=0;
    public CustomTimer(int secondi){
        t=new Timer();
        t.schedule(new RemindTask(),secondi*1000);
        timeIsUp=false;
        connections=0;
    }

    public boolean isTimeIsUp() {
        return timeIsUp;
    }

    public void setTimeIsUp(boolean timeIsUp) {
        this.timeIsUp = timeIsUp;
    }

    public int getConnections() {
        return connections;
    }

    public void setConnections(int connections) {
        this.connections = connections;
    }

    class RemindTask extends TimerTask{

        @Override
        public void run() {
            //System.out.println("Tempo scaduto");
            timeIsUp=true;
            t.cancel();
        }

    }


}
