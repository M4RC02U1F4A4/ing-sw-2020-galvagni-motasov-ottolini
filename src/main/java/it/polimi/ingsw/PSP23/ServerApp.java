package it.polimi.ingsw.PSP23;

import it.polimi.ingsw.PSP23.server.Server;
import java.io.IOException;

public class ServerApp {
    public static void main(String[] args) {
        try {
            while(true) {
                Server s = new Server();
                s.run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
