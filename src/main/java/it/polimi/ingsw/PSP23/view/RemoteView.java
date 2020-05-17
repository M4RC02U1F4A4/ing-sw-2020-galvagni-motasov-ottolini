package it.polimi.ingsw.PSP23.view;

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
        c.addObserver(new MessageReciever());
    }

    private class MessageReciever implements Observer<String>{

        @Override
        public void update(String message) {
            System.out.println("Ricevuto: "+message);
            clientSaid=message;
            try{
                String[] inputs=message.split(",");

                if(inputs[0].equals("MOVE")){
                    showMessage("MI MUOVO");
                    handleMove(Integer.parseInt(inputs[1]),Integer.parseInt(inputs[2]), Integer.parseInt(inputs[3]));
                    showMessage("E NON CRASHO");

                }
                else if(inputs[0].equals("BUILD")){
                    clientConnection.asyncSend("COSTRUISCO");
                    handleBuild(Integer.parseInt(inputs[1]),Integer.parseInt(inputs[2]), Integer.parseInt(inputs[3]));
                }
            }catch(IllegalArgumentException e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public void showMessage(Object message) {
        clientConnection.asyncSend(message);
    }


    @Override
    public void update(Message message) {
        showMessage(message.getMap());
        showMessage("aiuto");

        //VERIFICARE CHE NON SIA GAMEOVER
       /* if(message.getPlayer().getPlayerNumber()==)//non e' il tuo turno
            showMessage("non è il tuo turno");
          else{
             showMessage("è il tuo turno");
          }
        */

    }

    public String getWhatClientSaid(){
        return clientSaid;
    }
}
