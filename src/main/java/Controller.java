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

    private final int gridPrefWidth = 200;
    private final int menuBarPrefHeight = 20;
    private Pane pane;
    private Game game;
    private Label label = new Label("Info: ");
    private Button endTurn = new Button("End Turn");

    void init(){
        Stage stage = new Stage();
        stage.setScene(new Scene(root(),800,600));
        stage.setResizable(false);
        stage.show();
    }

    private Parent root(){
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(createMenu());
        borderPane.setRight(createGrid());
        borderPane.setCenter(pane);
        return borderPane;
    }

    private Node createMenu(){
        MenuBar menuBar = new MenuBar();
        menuBar.setPrefHeight(menuBarPrefHeight);
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
        HBox hBox1 = new HBox();
        hBox1.getChildren().add(endTurn);
        gridPane.add(hBox1,0,0);
        gridPane.add(label,0,1);
        return gridPane;
    }

    void setGame(Game game) {
        this.game = game;
    }

    Game getGame() {
        return game;
    }

    void setPane(){
        this.pane = new Pane((Group) game);
    }

    String selectRules() {
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
        return result.map(s -> "CLASSIC " + s).orElseGet(this::selectRules);
    }

    String connect() {
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

    void setLabel(String label) {
        this.label.setText("Info: " + label);
    }

    Button getEndTurn(){
        return endTurn;
    }
}
