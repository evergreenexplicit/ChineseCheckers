import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread{

    private static int PORT = 8901;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Controller controller = new Controller();
    private Game game;
    boolean bool = true;

    Client(String serverAddress) throws Exception{
        socket = new Socket(serverAddress,PORT);
        in = new BufferedReader(
                new InputStreamReader(
                socket.getInputStream()
        ));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void play() throws Exception{
        String response;
        try {
            response = in.readLine();
            if(response.startsWith("RULES_REQ")){
                String rules = controller.selectRules();
                out.println(rules);
                if(rules.startsWith("CLASSIC")){
                    setRules(rules);
                }
            } else {
                while(true){
                    response = in.readLine();
                    if(response.startsWith("CLASSIC"));
                    setRules(response);
                }
            }
        } finally {
            socket.close();
        }
    }


    private void setRules(String rules) {
        int players = Character.getNumericValue(rules.charAt(8));
        game = new Classic(4);
        controller.setGame(getGame());
        controller.setPane();
        controller.init();
        controller.getGame().startGame(580,600,players);
        for(int i=0; i<controller.getGame().getHorizontal(); i++){
            for(int j=0; j<controller.getGame().getVertical(); j++){
                int finalI = i;
                int finalJ = j;
                controller.getGame().getField(i,j).setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        out.println("CLICK "+ finalI +" "+ finalJ);
                    }
                });
            }
        }
    }

    public Controller getController() {
        return controller;
    }

    public Game getGame() {
        return game;
    }
}
