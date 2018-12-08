import javafx.scene.paint.Color;

public class Player {
    private String name;
    private Color color;

    Player(String name, Color color){
        this.name = name;
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
