import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

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
    private int clickCounter = 0;
    private int firstX;
    private int firstY;

    private Thread t = new Thread(){
        @Override
        public void run(){
            String response;
            while (true) {
                try {
                    response = in.readLine();
                    if(response!=null){
                        System.out.println("System: " + response);
                    }
                    if(response==null){
                        continue;
                    } else if (response.startsWith("MESSAGE")) {
                        String finalResponse = response;
                        Platform.runLater(() -> controller.setLabel(finalResponse.substring(8)));
                        if (response.substring(8).equals("Invalid move") ||
                                response.substring(8).equals("Move cancelled") ||
                                response.substring(8).equals("Not your pawn")||
                                response.substring(8).equals("Not your turn")){
                            game.revokeHighlighting();
                            clickCounter=0;
                        }
                    } else if (response.startsWith("POSSIBLE_MOVES")) {
                        String[] strings = response.split(" ");
                        int[] fields = new int[strings.length - 1];
                        for (int i = 1; i < strings.length; i++) {
                            fields[i - 1] = Integer.parseInt(strings[i]);
                        }
                        game.highlightPossibleMoves(fields);
                    } else if (response.startsWith("MOVED")) {
                        String[] strings = response.split(" ");
                        game.revokeHighlighting();
                        if (strings.length == 5) {
                            game.swapFields(Integer.parseInt(strings[1]), Integer.parseInt(strings[2]),
                                    Integer.parseInt(strings[3]), Integer.parseInt(strings[4]));
                        }
                    } else if (response.startsWith("GAME_OVER")) {
                        break;
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    Client(String serverAddress) throws Exception{
        socket = new Socket(serverAddress,PORT);
        in = new BufferedReader(
                new InputStreamReader(
                socket.getInputStream()
        ));
        out = new PrintWriter(socket.getOutputStream(), true);
    }


    public void play() throws Exception {
        String response;
        response = in.readLine();
        if (response.startsWith("RULES_REQ")) {
            String rules = controller.selectRules();
            out.println(rules);
            if (rules.startsWith("CLASSIC")) {
                setRules(rules);
            }
        } else if (response.startsWith("CLASSIC")){
            setRules(response);
        }
        t.start();
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
                controller.getGame().getField(i,j).setOnMouseClicked(event -> click(finalI,finalJ));
            }
        }
        controller.getEndTurn().setOnMouseClicked(event -> {
           out.println("END_TURN");
        });
    }

    private void click(int x, int y){
        if(clickCounter==0){
            System.out.println("CLIENT POSSIBLE_MOVES_REQ " + x + " " + y);
            out.println("POSSIBLE_MOVES_REQ " + x + " " + y);
            firstX = x;
            firstY = y;
            clickCounter = 1;
        } else {
            System.out.println("CLIENT MOVE_REQ " + firstX + " " + firstY + " " + x + " " + y);
            out.println("MOVE_REQ " + firstX + " " + firstY + " " + x + " " + y);
            firstX = -1;
            firstY = -1;
            clickCounter = 0;
        }
    }

    public Controller getController() {
        return controller;
    }

    public Game getGame() {
        return game;
    }
}
