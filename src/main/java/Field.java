import javafx.scene.shape.Circle;

public class Field extends Circle {
    private Player player;

    Field(double x, double y, double r, Player player){
        super(x,y,r);
        setPlayer(player);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }
}
