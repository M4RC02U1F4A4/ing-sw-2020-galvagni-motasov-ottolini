package it.polimi.ingsw.PSP23.view;

import it.polimi.ingsw.PSP23.model.God;
import it.polimi.ingsw.PSP23.model.Message;
import it.polimi.ingsw.PSP23.model.Player;
import it.polimi.ingsw.PSP23.observer.Observer;
import it.polimi.ingsw.PSP23.server.ClientConnection;

public class RemoteView extends View{
    private ClientConnection clientConnection;
    private String clientSaid="";

    public RemoteView(Player player, ClientConnection c) {
        super(player);
        this.clientConnection=c;
        c.addObserver(new MessageReceiver());
    }

    private class MessageReceiver implements Observer<String>{

        @Override
        public void update(String message) {
            clientSaid=message;
            try{
                String[] inputs=message.split(":");
                switch (inputs[0]){
                    case "SELECT_GODS":
                    case "CHOOSE_GOD":{
                        String[] divinita=inputs[1].split(",");
                        if(!God.getAllGods().contains(divinita[0])){
                            showMessage("Divinita' non valida, riprova");
                            return;
                        }

                    }
                    case "PLACE_WORKER":
                    case "CHOOSE_WORKER":
                    case "MOVE":
                    case "BUILD":
                        handleChoice(inputs[0],inputs[1]);
                        break;
                    case "SKIP":
                        handleChoice(inputs[0],"NULL");
                        break;
                    default:{
                        showMessage("Il comando inserito non è valido, riprova");
                    }
                }
            }catch(IllegalArgumentException e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public void showMessage(Object message) {
        clientConnection.send(message);
    }


    @Override
    public void update(Message message) {
        showMessage(message.getMap());
    }

    public String getWhatClientSaid(){
        return clientSaid;
    }

    public void close(){
        clientConnection.close();
    }
    public void isOver(){
        clientConnection.isOver();
    }
}
