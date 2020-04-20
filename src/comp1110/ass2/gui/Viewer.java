package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * A very simple viewer for piece placements in the Metro game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 */
public class Viewer extends Application {
    /* board layout */
    private static final int SQUARE_SIZE = 70;
    private static final int VIEWER_WIDTH = 1024;
    private static final int VIEWER_HEIGHT = 768;
    private static int X_OF_CORNER = 220;
    private static int Y_OF_CORNER = 70;
    private static int NUMER_OF_BLOCKS = 8;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group board = new Group();
    private final Group tiles = new Group();
    private TextField textField;




    class TileImage extends ImageView {
        int x, y;
        TileImage(String s) {
            setImage(new Image(Viewer.class.getResource(URI_BASE + s.charAt(0) + s.charAt(1) + s.charAt(2) + s.charAt(3) + ".jpg").toString()));
            super.setPreserveRatio(true);
            setFitWidth(SQUARE_SIZE);
            setFitHeight(SQUARE_SIZE);
            this.x = X_OF_CORNER + SQUARE_SIZE * (s.charAt(5) - '0');
            this.y = Y_OF_CORNER + SQUARE_SIZE * (s.charAt(4) - '0');
            setLayoutX(x);
            setLayoutY(y);
        }
    }
    class StationImage extends ImageView {
        StationImage(double x, double y, int no, int rotate) {
            setImage(new Image(Viewer.class.getResource(URI_BASE + "station"+no+".jpg").toString(),SQUARE_SIZE,SQUARE_SIZE,true,true));
            setLayoutX(x);
            setLayoutY(y);
            this.setRotate(rotate);
        }
    }

    class CentreStationImage extends ImageView {
        CentreStationImage(double x, double y, int rotate) {
            setImage(new Image(Viewer.class.getResource(URI_BASE + "centre_station"+".jpg").toString(),SQUARE_SIZE,SQUARE_SIZE,true,true));
            setLayoutX(x);
            setLayoutY(y);
            this.setRotate(rotate);
        }
    }



    /**
     * Draw a placement in the window, removing any previously drawn one
     * @author Yvonne(Xinyi) Zhang
     * @param placement A valid placement string
     */
    void makePlacement(String placement) {
        // FIXME Task 4: implement the simple placement viewer
        String[] tileArray = toTiles(placement);
        for (int i = 0; i < tileArray.length; i++){
            TileImage thisTile = new TileImage(tileArray[i]);
            tiles.getChildren().add(thisTile);
        }
    }



    private String[] toTiles(String placement){
        int count = placement.length() / 6;
        String[] tiles = new String[count];
        for(int i =0;i<count;i++){
            tiles[i] = placement.substring(6*i,6*i+6);
        }
        return tiles;
    }

    //draw the board itself without pieces
    void makeBoard() {

        //draw the vertical lines
        for (int i = 0; i < NUMER_OF_BLOCKS + 1 ; i++){
            Line line = new Line(X_OF_CORNER + SQUARE_SIZE * i , Y_OF_CORNER , X_OF_CORNER + SQUARE_SIZE * i , Y_OF_CORNER + SQUARE_SIZE * NUMER_OF_BLOCKS);
            if(i==0||i==NUMER_OF_BLOCKS)
                line.setStrokeWidth(3);
            board.getChildren().add(line);
        }

        //draw the horizontal lines
        for (int i = 0; i < NUMER_OF_BLOCKS + 1 ; i++){
            Line line = new Line( X_OF_CORNER , Y_OF_CORNER + SQUARE_SIZE * i , X_OF_CORNER + SQUARE_SIZE * NUMER_OF_BLOCKS , Y_OF_CORNER + SQUARE_SIZE * i);
            if(i==0||i==NUMER_OF_BLOCKS)
                line.setStrokeWidth(3);
            board.getChildren().add(line);
        }

        for(int i = 0;i < 8;i++){
            StationImage station = new StationImage(X_OF_CORNER+(NUMER_OF_BLOCKS-1)*SQUARE_SIZE-i*SQUARE_SIZE,Y_OF_CORNER-SQUARE_SIZE,i+1,180);
            board.getChildren().add(station);
        }
        for(int i = 0;i < 8;i++){
            StationImage station = new StationImage(X_OF_CORNER-SQUARE_SIZE,Y_OF_CORNER+SQUARE_SIZE*i,i+9,270);
            board.getChildren().add(station);
        }
        for(int i = 0;i < 8;i++){
            StationImage station = new StationImage(X_OF_CORNER+i*SQUARE_SIZE,Y_OF_CORNER+NUMER_OF_BLOCKS*SQUARE_SIZE,i+17,0);
            board.getChildren().add(station);
        }
        for(int i = 0;i < 8;i++){
            StationImage station = new StationImage(X_OF_CORNER+NUMER_OF_BLOCKS*SQUARE_SIZE,Y_OF_CORNER+NUMER_OF_BLOCKS*SQUARE_SIZE-(i+1)*SQUARE_SIZE,i+25,90);
            board.getChildren().add(station);
        }

        CentreStationImage centreStation1 = new CentreStationImage(430,280,270);
        board.getChildren().add(centreStation1);
        CentreStationImage centreStation2 = new CentreStationImage(430,350,180);
        board.getChildren().add(centreStation2);
        CentreStationImage centreStation3 = new CentreStationImage(500,280,0);
        board.getChildren().add(centreStation3);
        CentreStationImage centreStation4 = new CentreStationImage(500,350,90);
        board.getChildren().add(centreStation4);
    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                tiles.getChildren().clear();
                makePlacement(textField.getText());
                textField.clear();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FocusGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        makeBoard();
        root.getChildren().add(controls);
        root.getChildren().add(board);
        root.getChildren().add(tiles);

        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
