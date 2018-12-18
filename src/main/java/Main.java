import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Client client = new Client(new Controller().connect());
        client.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
