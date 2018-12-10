import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Board extends Group {

    Field[][] fields;
    int sideLength;
    int horizontal;
    int vertical;
    Color defaultColor = Color.BLACK;

    Board(int sideLength){
        this.sideLength = sideLength;
        this.horizontal = 6*sideLength+1;
        this.vertical = 4*sideLength+1;
        this.fields = new Field[horizontal][vertical];
    }

    public void fillFields (double height, double width, int players){
        double rh = (height-40)/(5 * Math.sqrt(3)* sideLength +2);
        double rw = (width - 40)/(7.5 * sideLength + 2);
        double radius = Math.min(rh,rw);
        System.out.println(height + " " + width + " " + rh + " " + rw + " " + radius);
        for (int j = 0; j < vertical; j++) {
            for (int i = 0; i < horizontal; i++) {
                double fieldX = 20 + ((1 + (i * 1.25)) * radius);
                double fieldY = 20 + ((1 + (j * 1.25 * Math.sqrt(3))) * radius);
                fields[i][j] = new Field(fieldX, fieldY, radius);
                fields[i][j].setFill(defaultColor);
                fields[i][j].setVisible(false);
                if (j < sideLength){
                    if ((i >= horizontal/2 - j) && (i <= horizontal/2 + j)){
                        fields[i][j].setVisible(true);
                    }
                } else if (j >= vertical - sideLength){
                    if ((i >= (horizontal / 2) - (vertical - j - 1)) && (i <= horizontal / 2 + (vertical - j - 1))){
                        fields[i][j].setVisible(true);
                    }
                } else {
                    if((i >= (horizontal / 2) - (2 * sideLength + Math.abs(2 * sideLength - j))) && (i <= (horizontal / 2) + (2 * sideLength + Math.abs(2 * sideLength - j)))){
                        fields[i][j].setVisible(true);
                    }
                }
                if (j % 2 == sideLength % 2) {
                    if (i % 2 == 1) {
                        fields[i][j].setVisible(false);
                    }
                } else {
                    if (i % 2 == 0) {
                        fields[i][j].setVisible(false);
                    }
                }
                if(fields[i][j].isVisible())
                    this.getChildren().add(fields[i][j]);
            }
        }
        if (players != 4){
            fillTop();
            if (players != 3){
                fillBottom();
            }
        }
        if (players != 2){
            fillBottomRight();
            fillBottomLeft();
            if (players != 3){
                fillTopRight();
                fillTopLeft();
            }
        }
    }

    private void fillTop(){
        for(int i = 0; i < horizontal; i++){
            for(int j = 0; j < sideLength; j++){
                if(fields[i][j].isVisible()){
                    fields[i][j].setFill(Color.RED);
                }
            }
        }
    }

    private void fillTopLeft(){
        for(int j = sideLength; j < 2 * sideLength; j++){
            for(int i = j - sideLength; i < 3 * sideLength - 1 - j; i++){
                if(fields[i][j].isVisible()){
                    fields[i][j].setFill(Color.ORANGE);
                }
            }
        }
    }

    private void fillBottomLeft(){
        for(int j = 3 * sideLength; j > 2 * sideLength; j--){
            for(int i = 3 * sideLength - j; i < j - sideLength - 1; i++){
                if(fields[i][j].isVisible()){
                    fields[i][j].setFill(Color.YELLOW);
                }
            }
        }
    }

    private void fillBottom(){
        for(int i = 0; i < horizontal; i++){
            for(int j = vertical - 1; j >= vertical - sideLength; j--){
                if(fields[i][j].isVisible()){
                    fields[i][j].setFill(Color.GREEN);
                }
            }
        }
    }

    private void fillTopRight(){
        for(int j = sideLength; j < 2 * sideLength; j++){
            for(int i = 3 * sideLength + 2 + j; i < 7 * sideLength + 1 - j; i++){
                if(fields[i][j].isVisible()){
                    fields[i][j].setFill(Color.PURPLE);
                }
            }
        }
    }

    private void fillBottomRight(){
        for(int j = 3 * sideLength; j > 2 * sideLength; j--){
            for(int i = 7 * sideLength + 2 - j; i < 3 * sideLength + 1 + j ;i++){
                if(fields[i][j].isVisible()){
                    fields[i][j].setFill(Color.BLUE);
                }
            }
        }
    }
}
