import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Checkers");
        primaryStage.setScene(new Scene(new Controller().root(),800,600));
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}
