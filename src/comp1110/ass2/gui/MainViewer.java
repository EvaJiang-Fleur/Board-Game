package comp1110.ass2.gui;

import comp1110.ass2.Metro;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * A very simple viewer for piece placements in the Metro game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 */
// game viewer
/**
 * @author Yvonne(Xinyi) Zhang and Xinyao Wang
 */
public class MainViewer extends Application {
    /* board layout */
    private static final int SQUARE_SIZE = 60;
    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 768;
    private static int X_OF_CORNER = 220;
    private static int Y_OF_CORNER = 70;
    private static int NUMER_OF_BLOCKS = 8;
    private final String a=Metro.drawFromDeck("","");
    public Viewer myViewer;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group board = new Group();
    private final Group tiles = new Group();
    private TextField textField;
    private TextField textField2;
    private TextField textField9;
    private TextField textField10;
    private TextField textField11;
    private TextField textField12;
    private TextField textField13;
    private TextField textField14;
    private TextField textField15;
    private TextField textField16;
    private TextField textField17;
    private TextField textField18;
    private TextField textField19;






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

    class CornerImage extends ImageView {
        CornerImage(double x, double y) {
            setImage(new Image(Viewer.class.getResource(URI_BASE + "tile_back_cover"+".jpg").toString(),SQUARE_SIZE,SQUARE_SIZE,true,true));
            setLayoutX(x);
            setLayoutY(y);
        }
    }

    class PieceImage extends ImageView {
        PieceImage(double x, double y, String piece) {
            setImage(new Image(Viewer.class.getResource(URI_BASE + piece+".jpg").toString(),SQUARE_SIZE*4,SQUARE_SIZE*4,true,true));
            setLayoutX(x);
            setLayoutY(y);
        }
    }

    class PieceImage2 extends ImageView {
        PieceImage2(double x, double y, String piece) {
            setImage(new Image(Viewer.class.getResource(URI_BASE + piece+".jpg").toString(),SQUARE_SIZE,SQUARE_SIZE,true,true));
            setLayoutX(x);
            setLayoutY(y);
        }
    }



    /**
     * Draw a placement in the window, removing any previously drawn one
     * @author Yvonne(Xinyi) Zhang
     * @param placement A valid placement string
     */
    void makePlacement(String placement) {

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
            StationImage station = new StationImage(X_OF_CORNER-SQUARE_SIZE,Y_OF_CORNER+SQUARE_SIZE*i,i+9,90);
            board.getChildren().add(station);
        }
        for(int i = 0;i < 8;i++){
            StationImage station = new StationImage(X_OF_CORNER+i*SQUARE_SIZE,Y_OF_CORNER+NUMER_OF_BLOCKS*SQUARE_SIZE,i+17,0);
            board.getChildren().add(station);
        }
        for(int i = 0;i < 8;i++){
            StationImage station = new StationImage(X_OF_CORNER+NUMER_OF_BLOCKS*SQUARE_SIZE,Y_OF_CORNER+NUMER_OF_BLOCKS*SQUARE_SIZE-(i+1)*SQUARE_SIZE,i+25,270);
            board.getChildren().add(station);
        }

        CentreStationImage centreStation1 = new CentreStationImage(400,250,270);
        board.getChildren().add(centreStation1);
        CentreStationImage centreStation2 = new CentreStationImage(400,310,180);
        board.getChildren().add(centreStation2);
        CentreStationImage centreStation3 = new CentreStationImage(460,250,0);
        board.getChildren().add(centreStation3);
        CentreStationImage centreStation4 = new CentreStationImage(460,310,90);
        board.getChildren().add(centreStation4);

        CornerImage cornerImage1 = new CornerImage(X_OF_CORNER-SQUARE_SIZE,Y_OF_CORNER-SQUARE_SIZE);
        board.getChildren().add(cornerImage1);
        CornerImage cornerImage2 = new CornerImage((X_OF_CORNER-SQUARE_SIZE)+SQUARE_SIZE*(NUMER_OF_BLOCKS+1),Y_OF_CORNER-SQUARE_SIZE);
        board.getChildren().add(cornerImage2);
        CornerImage cornerImage3 = new CornerImage(X_OF_CORNER-SQUARE_SIZE,(Y_OF_CORNER-SQUARE_SIZE)+SQUARE_SIZE*(NUMER_OF_BLOCKS+1));
        board.getChildren().add(cornerImage3);
        CornerImage cornerImage4 = new CornerImage((X_OF_CORNER-SQUARE_SIZE)+SQUARE_SIZE*(NUMER_OF_BLOCKS+1),(Y_OF_CORNER-SQUARE_SIZE)+SQUARE_SIZE*(NUMER_OF_BLOCKS+1));
        board.getChildren().add(cornerImage4);

    }

    void moveableTile(){ ;
        Pane node = generateNode(a);
        node.relocate(800, 60);
        draggable(node,a);
        root.getChildren().addAll(node);
    }

    private Pane generateNode(String piece) {
        Pane pane = new StackPane();
        PieceImage piece1 =new PieceImage(0,0,piece);
        pane.getChildren().addAll(piece1);
        return pane;
    }

    private static class Position {
        double x;
        double y;
    }
    private void draggable(Pane pane, String tileName) {
        final Position pos = new Position();
        pane.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            pos.x = event.getX();
            pos.y = event.getY();
            double x=event.getX();
            if (textField9.getText().length()<2){textField9.setText(a);}
        });
        pane.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            double x = pane.getLayoutX()+ event.getX() - pos.x;
            double y = pane.getLayoutY()+ event.getY() - pos.y;
            if (x>220&x<700&&y>70&&y<550)
            {x=((int)(x-160)/60)*60+160;
                y=((int)(y-10)/60)*60+10;}
            PieceImage2 piece1 =new PieceImage2(0,0,tileName);
            pane.getChildren().clear();
            pane.getChildren().addAll(piece1);
            pane.setOpacity(0.5);
            pane.relocate(x, y);
        });
        pane.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            pane.setOpacity(1.0);
            double x = pane.getLayoutX()+ event.getX() - pos.x;
            double y = pane.getLayoutY()+ event.getY() - pos.y;
            if (x>=220&x<=700&&y>=70&&y<=550)
            {x=((int)(x-160)/60)*60+160;
                y=((int)(y-10)/60)*60+10;}
            if (x>=220&x<=700&&y>=70&&y<=550)
                textField10.setText(""+(int)(y-70)/60+(int)(x-220)/60);
            if (Metro.isPlacementSequenceValid(textField13.getText()+textField9.getText()+""+(int)(y-70)/60+(int)(x-220)/60)){
                textField11.setText(textField9.getText()+""+(int)(y-70)/60+(int)(x-220)/60);
                textField13.setText(textField13.getText()+textField9.getText()+""+(int)(y-70)/60+(int)(x-220)/60);
                String a =Metro.drawFromDeck(textField13.getText() +textField11.getText(),"");
                textField9.setText(a);
                Pane node = generateNode(a);
                node.relocate(800, 60);
                draggable(node,a);
                root.getChildren().addAll(node);
                int i= (Integer.parseInt(textField12.getText()));
                if (textField12.getText().length()!=1||i<1||i>6) { textField12.setText("6");i=6;}
                if ((textField2.getText().toString().equals("1")||textField2.getText().equals("random"))&&
                        i>1&&i<7)
                { textField.setText(Metro.generateMove(textField13.getText(),a,i));
                    makePlacement(textField.getText());
                    textField13.setText(textField13.getText()+textField.getText());
                    String onea =Metro.drawFromDeck(textField13.getText() +textField11.getText(),"");
                    textField9.setText(onea);
                }
                else if (i>2&& textField2.getText().toString().equals("2")){
                    String twoa =Metro.generateMove(textField13.getText(),a,i);
                    String a1= Metro.drawFromDeck(textField13.getText()+twoa,"");
                    String twob =Metro.generateMove(textField13.getText()+twoa,a1,i);
                    textField.setText(twoa+twob);
                    makePlacement(textField.getText());
                    textField13.setText(textField13.getText()+textField.getText());
                    String b =Metro.drawFromDeck(textField13.getText() +textField11.getText(),"");
                    textField9.setText(b);
                }
                else if (i>3&& textField2.getText().toString().equals("3"))
                {   String threea =Metro.generateMove(textField13.getText(),a,i);
                    String a1= Metro.drawFromDeck(textField13.getText()+threea,"");
                    String threeb =Metro.generateMove(textField13.getText()+threea,a1,i);
                    String a2= Metro.drawFromDeck(textField13.getText()+threea+threeb,"");
                    String threec =Metro.generateMove(textField13.getText()+threea+threeb,a2,i);
                    textField.setText(threea+threeb+threec);
                    makePlacement(textField.getText());
                    textField13.setText(textField13.getText()+textField.getText());
                    String b =Metro.drawFromDeck(textField13.getText() +textField11.getText(),"");
                    textField9.setText(b);}
                else if (i>4&& textField2.getText().toString().equals("4"))
                {   String foura =Metro.generateMove(textField13.getText(),a,(Integer.parseInt(textField12.getText())));
                    String a1= Metro.drawFromDeck(textField13.getText()+foura,"");
                    String fourb =Metro.generateMove(textField13.getText()+foura,a1,i);
                    String a2= Metro.drawFromDeck(textField13.getText()+foura+fourb,"");
                    String fourc =Metro.generateMove(textField13.getText()+foura+fourb,a2,i);
                    String a3= Metro.drawFromDeck(textField13.getText()+foura+fourb+fourc,"");
                    String fourd =Metro.generateMove(textField13.getText()+foura+fourb+fourc,a3,i);
                    textField.setText(foura+fourb+fourc+fourd);
                    makePlacement(textField.getText());
                    textField13.setText(textField13.getText()+textField.getText());
                    String b =Metro.drawFromDeck(textField13.getText() +textField11.getText(),"");
                    textField9.setText(b);}
                else if (i>5&& textField2.getText().toString().equals("5"))
                {   String fivea =Metro.generateMove(textField13.getText(),a,(Integer.parseInt(textField12.getText())));
                    String a1= Metro.drawFromDeck(textField13.getText()+fivea,"");
                    String fiveb =Metro.generateMove(textField13.getText()+fivea,a1,i);
                    String a2= Metro.drawFromDeck(textField13.getText()+fivea+fiveb,"");
                    String fivec =Metro.generateMove(textField13.getText()+fivea+fiveb,a2,i);
                    String a3= Metro.drawFromDeck(textField13.getText()+fivea+fiveb+fivec,"");
                    String fived =Metro.generateMove(textField13.getText()+fivea+fiveb+fivec,a3,i);
                    String a4= Metro.drawFromDeck(textField13.getText()+fivea+fiveb+fivec+fived,"");
                    String fivee =Metro.generateMove(textField13.getText()+fivea+fiveb+fivec+fived,a4,i);
                    textField.setText(fivea+fiveb+fivec+fived+fivee);
                    makePlacement(textField.getText());
                    textField13.setText(textField13.getText()+textField.getText());
                    String b =Metro.drawFromDeck(textField13.getText() +textField11.getText(),"");
                    textField9.setText(b);}




            }else {textField11.setText("Wrong place");
            }
            if (Integer.parseInt(textField12.getText())>1&&Integer.parseInt(textField12.getText())<7)
                textField14.setText(""+ (Metro.getScore(textField13.getText(),Integer.parseInt(textField12.getText()))[0]));
            textField15.setText(""+ (Metro.getScore(textField13.getText(),Integer.parseInt(textField12.getText()))[1]));
            if (Integer.parseInt(textField12.getText())>2)
                textField16.setText(""+ (Metro.getScore(textField13.getText(),Integer.parseInt(textField12.getText()))[2]));
            if (Integer.parseInt(textField12.getText())>3)
                textField17.setText(""+ (Metro.getScore(textField13.getText(),Integer.parseInt(textField12.getText()))[3]));
            if (Integer.parseInt(textField12.getText())>4)
                textField18.setText(""+ (Metro.getScore(textField13.getText(),Integer.parseInt(textField12.getText()))[4]));
            if (Integer.parseInt(textField12.getText())>5)
                textField19.setText(""+ (Metro.getScore(textField13.getText(),Integer.parseInt(textField12.getText()))[5]));
        });
    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement(pc):");
        textField = new TextField();
        textField.setPrefWidth(200);
        Button button = new Button("Refresh");
        Button button2 = new Button("Clear");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                tiles.getChildren().clear();
                makePlacement(textField.getText());
//                textField.clear();
            }
        });
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                tiles.getChildren().clear();
                textField.clear();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button,button2);
        hb.setSpacing(10);
        hb.setLayoutX(1200);
        hb.setLayoutY(VIEWER_HEIGHT - 95);
        controls.getChildren().add(hb);

    }

    private void makeControls2() {
        Label label1 = new Label("tile name:");
        textField9 = new TextField();
        textField9.setPrefWidth(150);
        Label label2 = new Label("position:");
        textField10 = new TextField();
        textField10.setPrefWidth(50);
        Label label3 = new Label("TileCombine:");
        textField11 = new TextField();
        textField11.setPrefWidth(100);
        Label label4 = new Label("Number of player:");
        textField12 = new TextField();
        textField12.setPrefWidth(100);
        Label label5 = new Label("StrCombine");
        textField13 = new TextField();
        textField13.setPrefWidth(300);
        Label label6 = new Label("player1:");
        textField14 = new TextField();
        textField14.setPrefWidth(100);
        Label label7 = new Label("player2:");
        textField15 = new TextField();
        textField15.setPrefWidth(100);
        Label label8 = new Label("player3:");
        textField16 = new TextField();
        textField16.setPrefWidth(100);
        Label label9 = new Label("player4:");
        textField17 = new TextField();
        textField17.setPrefWidth(100);
        Label label10 = new Label("player5:");
        textField18 = new TextField();
        textField18.setPrefWidth(100);
        Label label11 = new Label("player6:");
        textField19 = new TextField();
        textField19.setPrefWidth(100);

        Label label12 = new Label("pc move:");
        textField2 = new TextField();
        textField2.setPrefWidth(50);

        HBox hb = new HBox();
        HBox hb2 = new HBox();
        HBox hb3 = new HBox();
        HBox hb4 = new HBox();
        HBox hb5 = new HBox();
        HBox hb6 = new HBox();
        hb.getChildren().addAll(label1,textField9);
        hb2.getChildren().addAll(label4,textField12);
        hb3.getChildren().addAll(label12,textField2);
        hb4.getChildren().addAll(label6,textField14,label7,textField15);
        hb5.getChildren().addAll(label8,textField16,label9,textField17);
        hb6.getChildren().addAll(label10,textField18,label11,textField19);
        hb.setLayoutX(1200);
        hb.setLayoutY(VIEWER_HEIGHT - 400);
        hb2.setLayoutX(810);
        hb2.setLayoutY(VIEWER_HEIGHT - 420);
        hb3.setLayoutX(855);
        hb3.setLayoutY(VIEWER_HEIGHT - 370);
        hb4.setLayoutX(800);
        hb4.setLayoutY(VIEWER_HEIGHT - 320);
        hb5.setLayoutX(800);
        hb5.setLayoutY(VIEWER_HEIGHT - 270);
        hb6.setLayoutX(800);
        hb6.setLayoutY(VIEWER_HEIGHT - 220);
        controls.getChildren().add(hb);
        controls.getChildren().add(hb2);
        controls.getChildren().add(hb3);
        controls.getChildren().add(hb4);
        controls.getChildren().add(hb5);
        controls.getChildren().add(hb6);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FocusGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
        makeBoard();
        moveableTile();
        root.getChildren().add(controls);
        root.getChildren().add(board);
        root.getChildren().add(tiles);
        Rectangle rectangle= new Rectangle(110,50);
        rectangle.setLayoutX(1000);
        rectangle.setLayoutY(630);
        rectangle.setFill(Color.BLACK);
        Label label=new Label("Exit");
        label.setLayoutX(1015);
        label.setLayoutY(635);
        label.setFont(Font.font("Verdana", FontWeight.BOLD,30));
        label.setTextFill(Color.WHITE);
        label.setOnMouseClicked(event -> {
            Menu1 info  = new Menu1();
            try {
                info.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            primaryStage.hide();
        });
        root.getChildren().addAll(rectangle,label);

        makeControls();
        makeControls2();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
