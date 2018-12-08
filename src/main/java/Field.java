import javafx.scene.shape.Circle;

public class Field extends Circle {
    private Player player;

    Field(double x, double y, double r){
        super(x,y,r);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }
}
