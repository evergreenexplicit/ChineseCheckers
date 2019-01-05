import javafx.application.Platform;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Controller {

    private final int gridPrefWidth = 200;
    private final int menuBarPrefHeight = 20;
    private Stage stage;
    private Pane pane;
    private Game game;
    private Label infoLabel = new Label("Info: ");
    private Button endTurn = new Button("End Turn");
    private Circle playersCircle = new Circle(0);

    void init(){
        stage = new Stage();
        stage.setScene(new Scene(root(),800,600));
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
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
        infoMenu.getItems().addAll(/*rulesInfo,*/authorInfo);
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
        Label label = new Label("Your pawns: ");
        playersCircle.setRadius(10);
        HBox hBox1 = new HBox();
        hBox1.getChildren().add(endTurn);
        gridPane.add(hBox1,0,0);
        gridPane.add(infoLabel,0,1);
        gridPane.add(label,0,2);
        gridPane.add(playersCircle,1,2);
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


    public double getPaneHeight(){
        System.out.println(pane.getHeight());
        return pane.getHeight();
    }

    public double getPaneWidth(){
        System.out.println(pane.getWidth());
        return pane.getWidth();
    }

    void setInfoLabel(String infoLabel) {
        this.infoLabel.setText("Info: " + infoLabel);
    }


    Button getEndTurn(){
        return endTurn;
    }


    void serverError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("An exception has occurred");
        alert.setContentText("Server seems to have stopped, your game will end now");
        alert.showAndWait();
        stage.close();
    }

    void setPlayersCircleColor(Color color) {
        playersCircle.setFill(color);
    }

    boolean youWin(boolean win){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if(win){
            alert.setTitle("You won");
            alert.setHeaderText("YOU WON, Do you want to continue spectating?");
        } else {
            alert.setTitle("You lost");
            alert.setHeaderText("YOU LOST, Do you want to continue spectating?");
        }
        alert.setContentText("Press OK to confirm or CANCEL to quit the game");

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    void gameOver(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Game has finished, thank you for playing!");
        alert.setContentText("The game will close now");
        alert.showAndWait();
        stage.close();
    }
}
