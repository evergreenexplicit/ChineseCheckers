import javafx.scene.Group;

public class Board extends Group {

    Field[][] fields;
    int n;
    int horizontal;
    int vertical;
    Board(int n){
        this.n = n;
        this.horizontal = 6*n+1;
        this.vertical = 4*n+1;
        this.fields = new Field[horizontal][vertical];
    }

    public void fillFields (double height, double width){
        double rh = (height-40)/(5 * Math.sqrt(3)* n +2);
        double rw = (width - 40)/(7.5 * n + 2);
        double radius = Math.min(rh,rw);
        System.out.println(height + " " + width + " " + rh + " " + rw + " " + radius);
        for (int j = 0; j < vertical; j++) {
            for (int i = 0; i < horizontal; i++) {
                double fieldX = 20 + ((1 + (i * 1.25)) * radius);
                double fieldY = 20 + ((1 + (j * 1.25 * Math.sqrt(3))) * radius);
                fields[i][j] = new Field(fieldX, fieldY, radius);
                fields[i][j].setVisible(false);
                if (j < n){
                    if ((i >= horizontal/2 - j) && (i <= horizontal/2 + j)){
                        fields[i][j].setVisible(true);
                    }
                } else if (j >= vertical - n){
                    if ((i >= (horizontal / 2) - (vertical - j - 1)) && (i <= horizontal / 2 + (vertical - j - 1))){
                        fields[i][j].setVisible(true);
                    }
                } else {
                    if((i >= (horizontal / 2) - (2 * n + Math.abs(2 * n - j))) && (i <= (horizontal / 2) + (2 * n + Math.abs(2 * n - j)))){
                        fields[i][j].setVisible(true);
                    }
                }
                if (j % 2 == n % 2) {
                    if (i % 2 == 1) {
                        fields[i][j].setVisible(false);
                    }
                } else {
                    if (i % 2 == 0) {
                        fields[i][j].setVisible(false);
                    }
                }
                this.getChildren().add(fields[i][j]);
            }
        }
    }


}
