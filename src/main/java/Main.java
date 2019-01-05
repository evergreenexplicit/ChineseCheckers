import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.util.Optional;

import static java.lang.System.exit;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Client client = new Client(connect());
            client.play();
        } catch (Exception e){
            cannotConnect();
            exit(-1);
        }
    }

    private String connect() {
        TextInputDialog dialog = new TextInputDialog("localhost");
        dialog.setTitle("Connect to server");
        dialog.setHeaderText(null);
        dialog.setContentText("Socket:");

        Optional<String> result = dialog.showAndWait();
        return result.orElseGet(this::connect);
    }

    void cannotConnect(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Could not connect to server");
        alert.setContentText("Server is not working or \n" +
                "You have given wrong address or \n" +
                "There is a game running already");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
