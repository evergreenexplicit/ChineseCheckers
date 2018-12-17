import javafx.scene.paint.Color;

public interface Game{
    void fillFields(double height, double width, int players);
    void setFieldDefaultColor(int horizontal, int vertical);
    void setFieldColor(int horizontal, int vertical, Color color);
    Color getFieldColor(int horizontal, int vertical);
    /*void fillTop();
    void fillTopLeft();
    void fillTopRight();
    void fillBottom();
    void fillBottomRight();
    void fillBottomLeft();*/
    void swapFields(int firstHorizontal, int firstVertical, int secondHorizontal, int secondVertical);
    void test();
}
