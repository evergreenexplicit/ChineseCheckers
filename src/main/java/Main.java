import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Checkers");
        Controller controller = new Controller();
        primaryStage.setScene(new Scene(controller.root(),800,600));
        primaryStage.setResizable(false);
        primaryStage.show();
        Client client = new Client(controller.connect());
        client.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
