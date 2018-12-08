import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Controller {

    public Parent root(){
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menu());
        borderPane.setRight(grid());
        borderPane.setCenter(pane());
        borderPane.setBottom(label());
        return borderPane;
    }

    public Node menu(){
        MenuBar menuBar = new MenuBar();
        Menu gameMenu = new Menu("Game");
        MenuItem newGame = new MenuItem("New Game");
        return menuBar;
    };

    public Node grid(){
        GridPane gridPane = new GridPane();

        return gridPane;
    }

    public Node pane(){
        Pane pane = new Pane();

        return pane;
    }

    public Node label(){
        Label label = new Label();

        return label;
    }
}
