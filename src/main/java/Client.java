import javafx.application.Platform;
import javafx.scene.paint.Color;

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
    private int players;
    private int player;

    private Thread t = new Thread() {
        @Override
        public void run() {
            String response;
            try {
                while (true) {
                    response = in.readLine();
                    if (response != null) {
                        System.out.println("System: " + response);
                    }
                    if (response == null) {
                        continue;
                    } else if (response.startsWith("MESSAGE")) {
                        String finalResponse = response;
                        String message = response.substring(8);
                        Platform.runLater(() -> controller.setInfoLabel(finalResponse.substring(8)));
                        if (message.equals("Invalid move") ||
                                message.equals("Move cancelled") ||
                                message.equals("Not your pawn") ||
                                message.equals("Not your turn")) {
                            game.revokeHighlighting();
                            clickCounter = 0;
                        } else if (message.startsWith("You are player")) {
                            player = Integer.parseInt(message.substring(15));
                            setCircleColor(player, players);
                        }
                    } else if (response.startsWith("WIN")) {
                        int k = Integer.parseInt(response.substring(4));
                        if (!controller.youWin(k)) {
                            break;
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
                    } else if (response.startsWith("END")) {
                        break;
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                //e.printStackTrace();
                Platform.runLater(() -> controller.serverError());
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    //e.printStackTrace();
                    Platform.runLater(() -> controller.serverError());
                }
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


    void play() throws Exception {
        String response;
        response = in.readLine();
        System.out.println(response);
        if (response.startsWith("RULES_REQ")) {
            player = 0;
            String rules = controller.selectRules();
            out.println(rules);
            if (rules.startsWith("CLASSIC")) {
                setRules(rules);
            }
            setCircleColor(player,players);
        }
        if (response.startsWith("CLASSIC")){
            setRules(response);
        }
        t.start();
    }


    private void setRules(String rules) {
        players = Character.getNumericValue(rules.charAt(8));
        game = new Classic(4);
        controller.setGame(game);
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
           out.println("END_TURN_REQ");
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

    private void setCircleColor(int player, int players){
        if(player == 0){
            if(players != 4){
                controller.setPlayersCircleColor(Color.RED);
            }
            else {
                controller.setPlayersCircleColor(Color.PURPLE);
            }
        } else if (player == 1) {
            if (players == 6) {
                controller.setPlayersCircleColor(Color.PURPLE);
            } else if (players == 2){
                controller.setPlayersCircleColor(Color.GREEN);
            } else {
                controller.setPlayersCircleColor(Color.BLUE);
            }
        } else if (player == 2){
            if (players == 6){
                controller.setPlayersCircleColor(Color.BLUE);
            } else {
                controller.setPlayersCircleColor(Color.YELLOW);
            }
        } else if (player == 3){
            if (players == 4){
                controller.setPlayersCircleColor(Color.ORANGE);
            } else {
                controller.setPlayersCircleColor(Color.GREEN);
            }
        } else if (player == 4){
            controller.setPlayersCircleColor(Color.YELLOW);
        } else {
            controller.setPlayersCircleColor(Color.ORANGE);
        }
    }
}
