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
        clientConnection.addObserver(new MessageReciever());
    }

    private class MessageReciever implements Observer<String>{

        @Override
        public void update(String message) {
            System.out.println("Ricevuto: "+message);
            try{
                String[] inputs=message.split(" ");
                if(inputs[0]=="MOVE"){
                    handleMove(Integer.parseInt(inputs[1]),Integer.parseInt(inputs[2]));
                }
                else if(inputs[0]=="BUILD"){
                    handleBuild(Integer.parseInt(inputs[1]),Integer.parseInt(inputs[2]));
                }
            }catch(IllegalArgumentException e){
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void showMessage(Object message) {
        clientConnection.asyncSend(message);
    }

    @Override
    public void update(Message message) {
        showMessage(message.getMap());
        //VERIFICARE CHE NON SIA GAMEOVER
       /* if(message.getPlayer().getPlayerNumber()==)//non e' il tuo turno
            showMessage("non è il tuo turno");
          else{
             showMessage("è il tuo turno");
          }
        */
    }
}
