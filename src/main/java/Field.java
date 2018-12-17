import javafx.scene.shape.Circle;

public class Field extends Circle {

    private int guiX;
    private int guiY;
    private String response;

    Field(double x, double y, double r, int horizontal, int vertical){
        super(x,y,r);
        this.guiX = horizontal;
        this.guiY = vertical;
    }

    public int getGuiX() {
        return guiX;
    }

    public int getGuiY() {
        return guiY;
    }

    public String getResponse() {
        return response;
    }
}
