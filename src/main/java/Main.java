import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Checkers");
        Client client = new Client(new Controller().connect());
        primaryStage.setScene(new Scene(client.getController().root(),800,600));
        primaryStage.setResizable(false);
        primaryStage.show();
        client.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
