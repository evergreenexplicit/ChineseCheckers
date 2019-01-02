import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Controller {

    final int gridPrefWidth = 200;
    final int menuBarPrefHeight = 20;
    Pane pane;
    Game game;

    void init(){
        Stage stage = new Stage();
        stage.setScene(new Scene(root(),800,600));
        stage.setResizable(false);
        stage.show();
    }

    Parent root(){
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(createMenu());
        borderPane.setRight(createGrid());
        borderPane.setCenter(pane);
        return borderPane;
    }

    private Node createMenu(){
        MenuBar menuBar = new MenuBar();
        menuBar.setPrefHeight(menuBarPrefHeight);
        //Menu gameMenu = new Menu("Game");
        MenuItem newGame = new MenuItem("New Game");
        MenuItem customGame = new MenuItem("Customize Rules");
        //gameMenu.getItems().addAll(newGame,customGame);
        Menu infoMenu = new Menu("Info");
        MenuItem rulesInfo = new MenuItem("Rules");
        MenuItem authorInfo = new MenuItem("Authors");
        infoMenu.getItems().addAll(rulesInfo,authorInfo);
        menuBar.getMenus().addAll(/*gameMenu,*/infoMenu);
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
        test2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            }
        });
        gridPane.add(hBox2,0,2,2,1);
        return gridPane;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setPane(){
        this.pane = new Pane((Group) game);
    }

    public String selectRules() {
        List<String> choices = new ArrayList<>();
        choices.add("2");
        choices.add("3");
        choices.add("4");
        choices.add("6");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("2", choices);
        dialog.setTitle("Number of Players");
        dialog.setHeaderText("Select number of players");
        dialog.setContentText("Players:");

        Optional<String> result = dialog.showAndWait();
        return result.map(s -> "CLASSIC_" + s).orElseGet(this::selectRules);
    }

    public String connect() {
        TextInputDialog dialog = new TextInputDialog("localhost");
        dialog.setTitle("Connect to server");
        dialog.setHeaderText(null);
        dialog.setContentText("Socket:");

        Optional<String> result = dialog.showAndWait();
        return result.orElseGet(this::connect);
    }

    public double getPaneHeight(){
        System.out.println(pane.getHeight());
        return pane.getHeight();
    }

    public double getPaneWidth(){
        System.out.println(pane.getWidth());
        return pane.getWidth();
    }



    /*public Node label(){
        Label label = new Label();

        return label;
    }*/
}
