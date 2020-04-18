package it.polimi.ingsw.PSP23.view;

import it.polimi.ingsw.PSP23.model.MoveOrBuildMessage;
import it.polimi.ingsw.PSP23.model.Player;
import it.polimi.ingsw.PSP23.observer.Observer;

public class RemoteView extends View{

    protected RemoteView(Player player) {
        super(player);
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
    public void update(MoveOrBuildMessage message) {

    }
}
