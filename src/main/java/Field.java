import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class Field extends Circle {



    Field(double x, double y, double r){
        super(x,y,r);
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                //wyslanie zapytania do serwera
            }
        });
    }


}
