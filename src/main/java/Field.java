import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class Field extends Circle {

    private int horizontal;
    private int vertical;
    private String response;

    Field(double x, double y, double z){
        super(x,y,z);
    }

    Field(double x, double y, double r, final int horizontal, final int vertical){
        super(x,y,r);
        this.horizontal=horizontal;
        this.vertical=vertical;
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                response = "CLICKED_" + getHorizontal() + "_" + getVertical();
            }
        });
    }

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertical() {
        return vertical;
    }
}
