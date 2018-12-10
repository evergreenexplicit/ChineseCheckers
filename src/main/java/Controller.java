import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Controller {

    final int gridPrefWidth = 200;
    final int menuBarPrefHeight = 20;
    Pane pane;
    Board group = new Board(4);

    Parent root(){
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(createMenu());
        borderPane.setRight(createGrid());
        pane = (Pane) createPane();
        borderPane.setCenter(pane);
        //borderPane.setBottom(label());
        return borderPane;
    }

    private Node createMenu(){
        MenuBar menuBar = new MenuBar();
        menuBar.setPrefHeight(menuBarPrefHeight);
        Menu gameMenu = new Menu("Game");
        MenuItem newGame = new MenuItem("New Game");
        MenuItem customGame = new MenuItem("Customize Rules");
        gameMenu.getItems().addAll(newGame,customGame);
        Menu infoMenu = new Menu("Info");
        MenuItem rulesInfo = new MenuItem("Rules");
        MenuItem authorInfo = new MenuItem("Authors");
        infoMenu.getItems().addAll(rulesInfo,authorInfo);
        menuBar.getMenus().addAll(gameMenu,infoMenu);
        return menuBar;
    }

    private Node createGrid(){
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(20));
        gridPane.setPrefWidth(gridPrefWidth);
        Button startGame = new Button("Start Game");
        HBox hBox1 = new HBox();
        hBox1.getChildren().add(startGame);
        gridPane.add(hBox1,0,0);
        Label playerInfo = new Label("Current Player: ");
        gridPane.add(playerInfo,0,1);
        Label currPlayer = new Label("Placeholder");
        gridPane.add(currPlayer,1,1);
        Button test = new Button("Test");
        Button test2 = new Button("Test");
        HBox hBox2 = new HBox();
        hBox2.setAlignment(Pos.CENTER);
        hBox2.getChildren().addAll(test,test2);
        test.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                group.fillFields(pane.getHeight(), pane.getWidth(), 2);
            }
        });
        test2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            }
        });
        gridPane.add(hBox2,0,2,2,1);
        return gridPane;
    }

    private Node createPane(){
        return new Pane(group);
    }


    /*public Node label(){
        Label label = new Label();

        return label;
    }*/
}
