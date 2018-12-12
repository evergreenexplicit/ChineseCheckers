import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class Field extends Circle {

    private int horizontal;
    private int vertical;
    private String response;

    Field(double x, double y, double r, int horizontal, int vertical){
        super(x,y,r);
        this.horizontal=horizontal;
        this.vertical=vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public String getResponse() {
        return response;
    }
}
