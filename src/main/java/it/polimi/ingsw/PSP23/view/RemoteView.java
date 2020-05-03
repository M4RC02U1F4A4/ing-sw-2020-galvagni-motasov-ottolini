package it.polimi.ingsw.PSP23.view;

import it.polimi.ingsw.PSP23.model.Message;
import it.polimi.ingsw.PSP23.model.Player;
import it.polimi.ingsw.PSP23.observer.Observer;
import it.polimi.ingsw.PSP23.server.ClientConnection;

public class RemoteView extends View{
    private ClientConnection clientConnection;

    protected RemoteView(Player player,ClientConnection clientConnection) {
        super(player);
        this.clientConnection=clientConnection;
    }

    private class MessageReciever implements Observer<String>{

        @Override
        public void update(String message) {
            System.out.println("Ricevuto: "+message);

        }
    }

    @Override
    protected void showMessage(Object message) {

    }

    @Override
    public void update(Message message) {
        clientConnection.asyncSend(message.getMap());


    }
}
