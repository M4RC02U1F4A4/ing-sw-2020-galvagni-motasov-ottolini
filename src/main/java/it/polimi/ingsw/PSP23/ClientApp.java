package it.polimi.ingsw.PSP23;

import it.polimi.ingsw.PSP23.client.Client;
import it.polimi.ingsw.PSP23.model.Map;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class ClientApp
{
    public static void main( String[] args ) {
        Client c=new Client("127.0.0.1",13245);
        try {
            c.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
