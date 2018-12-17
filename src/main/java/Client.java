import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private static int PORT = 8901;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Controller controller = new Controller();
    Game game;

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
            if(response.startsWith("FIRST")){
                out.println(new Controller().selectRules());
            }
            while(true){
                response = in.readLine();
                if (response.startsWith("TEST")){
                    break;
                }
            }
        } finally {
            socket.close();
        }
    }

    public Controller getController() {
        return controller;
    }
}
