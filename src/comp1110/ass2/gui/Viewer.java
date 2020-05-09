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
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * A very simple viewer for piece placements in the Metro game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 */
public class Viewer extends Application {
    /* board layout */
    private static final int SQUARE_SIZE = 60;
    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 768;
    private static int X_OF_CORNER = 220;
    private static int Y_OF_CORNER = 70;
    private static int NUMER_OF_BLOCKS = 8;
    public Viewer myViewer;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group board = new Group();
    private final Group tiles = new Group();
    private TextField textField;
    private TextField textField2;
    private TextField textField3;
    private TextField textField4;
    private TextField textField5;
    private TextField textField6;
    private TextField textField7;
    private TextField textField8;
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

    void moveableTile(){
        for (int i=0;i<4;i++){
            Pane node1 = generateNode("aacb");
            Pane node2 = generateNode("cbaa");
            Pane node3 = generateNode("acba");
            Pane node4 = generateNode("baac");
            Pane node5 = generateNode("aaaa");
            node1.relocate(800, 60);
            node2.relocate(870, 60);
            node3.relocate(940, 60);
            node4.relocate(1010, 60);
            node5.relocate(800, 130);
            draggable(node1);
            draggable(node2);
            draggable(node3);
            draggable(node4);
            draggable(node5);
            root.getChildren().addAll(node1,node2,node3,node4,node5);
        }
        for (int i=0;i<3;i++){
            Pane node1 = generateNode("cbcb");
            Pane node2 = generateNode("bcbc");
            node1.relocate(870, 130);
            node2.relocate(940, 130);
            draggable(node1);
            draggable(node2);
            root.getChildren().addAll(node1,node2);
        }
        for (int i=0;i<2;i++){
            Pane node1 = generateNode("cccc");
            Pane node2 = generateNode("bbbb");
            Pane node3 = generateNode("dacc");
            Pane node4 = generateNode("cdac");
            Pane node5 = generateNode("ccda");
            Pane node6 = generateNode("accd");
            Pane node7 = generateNode("dbba");
            Pane node8 = generateNode("adbb");
            Pane node9 = generateNode("badb");
            Pane node10 = generateNode("bbad");
            Pane node11 = generateNode("ddbc");
            Pane node12 = generateNode("cddb");
            Pane node13 = generateNode("bcdd");
            Pane node14 = generateNode("dbcd");
            Pane node15 = generateNode("adad");
            Pane node16 = generateNode("dada");
            Pane node17 = generateNode("dddd");
            node1.relocate(1010, 130);
            node2.relocate(800, 200);
            node3.relocate(870, 200);
            node4.relocate(940, 200);
            node5.relocate(1010, 200);
            node6.relocate(800, 270);
            node7.relocate(870, 270);
            node8.relocate(940, 270);
            node9.relocate(1010, 270);
            node10.relocate(800, 340);
            node11.relocate(870, 340);
            node12.relocate(940, 340);
            node13.relocate(1010, 340);
            node14.relocate(800, 410);
            node15.relocate(870, 410);
            node16.relocate(940, 410);
            node17.relocate(1010, 410);
            draggable(node1);
            draggable(node2);
            draggable(node3);
            draggable(node4);
            draggable(node5);
            draggable(node6);
            draggable(node7);
            draggable(node8);
            draggable(node9);
            draggable(node10);
            draggable(node11);
            draggable(node12);
            draggable(node13);
            draggable(node14);
            draggable(node15);
            draggable(node16);
            draggable(node17);
            root.getChildren().addAll(node1,node2,node3,node4,node5,node6,node7,node8,node9,node10,
                    node11,node12,node13,node14,node15,node16,node17);
        }

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
    private void draggable(Pane pane) {
        final Position pos = new Position();
        pane.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            pos.x = event.getX();
            pos.y = event.getY();
            double x=event.getX();
            if (pane.getLayoutY()>=60&&pane.getLayoutY()<=120){
                if (pane.getLayoutX()>=800&&pane.getLayoutX()<=860){textField9.setText("aacb");}
                else if (pane.getLayoutX()>=870&&pane.getLayoutX()<=930){textField9.setText("cbaa");}
                else if (pane.getLayoutX()>=940&&pane.getLayoutX()<=1000){textField9.setText("acba");}
                else if (pane.getLayoutX()>=1010&&pane.getLayoutX()<=1070){textField9.setText("baac");}
            }else if (pane.getLayoutY()>=130&&pane.getLayoutY()<=190){
                if (pane.getLayoutX()>=800&&pane.getLayoutX()<=860){textField9.setText("aaaa");}
                else if (pane.getLayoutX()>=870&&pane.getLayoutX()<=930){textField9.setText("cbcb");}
                else if (pane.getLayoutX()>=940&&pane.getLayoutX()<=1000){textField9.setText("bcbc");}
                else if (pane.getLayoutX()>=1010&&pane.getLayoutX()<=1070){textField9.setText("cccc");}
            }else if (pane.getLayoutY()>=200&&pane.getLayoutY()<=260){
                if (pane.getLayoutX()>=800&&pane.getLayoutX()<=860){textField9.setText("bbbb");}
                else if (pane.getLayoutX()>=870&&pane.getLayoutX()<=930){textField9.setText("dacc");}
                else if (pane.getLayoutX()>=940&&pane.getLayoutX()<=1000){textField9.setText("cdac");}
                else if (pane.getLayoutX()>=1010&&pane.getLayoutX()<=1070){textField9.setText("ccda");}
            }else if (pane.getLayoutY()>=270&&pane.getLayoutY()<=330){
                if (pane.getLayoutX()>=800&&pane.getLayoutX()<=860){textField9.setText("accd");}
                else if (pane.getLayoutX()>=870&&pane.getLayoutX()<=930){textField9.setText("dbba");}
                else if (pane.getLayoutX()>=940&&pane.getLayoutX()<=1000){textField9.setText("adbb");}
                else if (pane.getLayoutX()>=1010&&pane.getLayoutX()<=1070){textField9.setText("badb");}
            } else if (pane.getLayoutY()>=340&&pane.getLayoutY()<=400){
                if (pane.getLayoutX()>=800&&pane.getLayoutX()<=860){textField9.setText("bbad");}
                else if (pane.getLayoutX()>=870&&pane.getLayoutX()<=930){textField9.setText("ddbc");}
                else if (pane.getLayoutX()>=940&&pane.getLayoutX()<=1000){textField9.setText("cddb");}
                else if (pane.getLayoutX()>=1010&&pane.getLayoutX()<=1070){textField9.setText("bcdd");}
            }else if (pane.getLayoutY()>=400&&pane.getLayoutY()<=460){
                if (pane.getLayoutX()>=800&&pane.getLayoutX()<=860){textField9.setText("dbcd");}
                else if (pane.getLayoutX()>=870&&pane.getLayoutX()<=930){textField9.setText("adad");}
                else if (pane.getLayoutX()>=940&&pane.getLayoutX()<=1000){textField9.setText("dada");}
                else if (pane.getLayoutX()>=1010&&pane.getLayoutX()<=1070){textField9.setText("dddd");}
            }
        });
        pane.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            double x = pane.getLayoutX()+ event.getX() - pos.x;
            double y = pane.getLayoutY()+ event.getY() - pos.y;
            if (x>220&x<700&&y>70&&y<550)
            {x=((int)(x-160)/60)*60+160;
                y=((int)(y-10)/60)*60+10;}
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
            }else {
                textField11.setText("Wrong place");
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
        Label label1 = new Label("Placement:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        Label label2 = new Label("PlayerNum:");
        textField2 = new TextField();
        textField.setPrefWidth(300);
        Label label3 = new Label("Scores Player1:");
        textField3 = new TextField();
        textField3.setPrefWidth(80);
        Label label4 = new Label("Player2:");
        textField4 = new TextField();
        textField4.setPrefWidth(80);
        Label label5 = new Label("Player3:");
        textField5 = new TextField();
        textField5.setPrefWidth(80);
        Label label6 = new Label("Player4:");
        textField6 = new TextField();
        textField6.setPrefWidth(80);
        Label label7 = new Label("Player5:");
        textField7 = new TextField();
        textField7.setPrefWidth(80);
        Label label8 = new Label("Player6:");
        textField8 = new TextField();
        textField8.setPrefWidth(80);
        Button button2 = new Button("Clear");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                tiles.getChildren().clear();
                makePlacement(textField.getText());
                int i=Integer.parseInt(textField2.getText());
                int[]a=new int[i];
                a=Metro.getScore(textField.getText(),Integer.parseInt(textField2.getText()));
                textField3.setText(""+a[0]);
                textField4.setText(""+a[1]);
                if (Integer.parseInt(textField2.getText())>2)textField5.setText(""+a[2]);
                if (Integer.parseInt(textField2.getText())>3)textField6.setText(""+a[3]);
                if (Integer.parseInt(textField2.getText())>4)textField7.setText(""+a[4]);
                if (Integer.parseInt(textField2.getText())>5)textField8.setText(""+a[5]);
//                textField.clear();
                String b=textField.getText();
                if (!Metro.isPlacementSequenceValid(b)&&(i>6||i<2)){
                    textField.setText("WrongStringSequence");
                    textField2.setText("WrongNumber");
                } else if( Metro.isPlacementSequenceValid(b)&&(i>6||i<2)){
                    textField2.setText("WrongNumber");
                }else if (!Metro.isPlacementSequenceValid(b)){
                    textField.setText("WrongStringSequence");
                }
            }
        });
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                tiles.getChildren().clear();
                textField.clear();
                textField2.clear();
                textField3.clear();
                textField4.clear();
                textField5.clear();
                textField6.clear();
                textField7.clear();
                textField8.clear();
            }
        });
        HBox hb = new HBox();
        HBox hb2 = new HBox();
        hb.getChildren().addAll(label1, textField, button,label2,textField2,button2);
        hb2.getChildren().addAll(label3,textField3,label4,textField4,label5,textField5,label6,textField6,
                label7,textField7,label8,textField8);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 150);
        hb2.setLayoutX(130);
        hb2.setLayoutY(VIEWER_HEIGHT-125);
        controls.getChildren().add(hb);
        controls.getChildren().add(hb2);
    }

    private void makeControls2() {
        Label label1 = new Label("tile:");
        textField9 = new TextField();
        textField9.setPrefWidth(150);
        Label label2 = new Label("position:");
        textField10 = new TextField();
        textField10.setPrefWidth(100);
        Button button = new Button("NewGame");
        Label label3 = new Label("TileCombine:");
        textField11 = new TextField();
        textField11.setPrefWidth(150);
        Label label4 = new Label("PlayerNum");
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
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) { ;
            }
        });
        HBox hb = new HBox();
        HBox hb2 = new HBox();
        HBox hb3 = new HBox();
        HBox hb4 = new HBox();
        HBox hb5 = new HBox();
        hb.getChildren().addAll(label1,textField9, label2,textField10);
        hb2.getChildren().addAll(label3,textField11, label4,textField12);
        hb3.getChildren().addAll(label5,textField13);
        hb4.getChildren().addAll(label6,textField14,label7,textField15,label8,textField16);
        hb5.getChildren().addAll(label9,textField17,label10,textField18,label11,textField19);
        hb.setLayoutX(780);
        hb.setLayoutY(VIEWER_HEIGHT - 290);
        hb2.setLayoutX(780);
        hb2.setLayoutY(VIEWER_HEIGHT - 263);
        hb3.setLayoutX(780);
        hb3.setLayoutY(VIEWER_HEIGHT - 236);
        hb4.setLayoutX(780);
        hb4.setLayoutY(VIEWER_HEIGHT - 209);
        hb5.setLayoutX(780);
        hb5.setLayoutY(VIEWER_HEIGHT - 182);
        controls.getChildren().add(hb);
        controls.getChildren().add(hb2);
        controls.getChildren().add(hb3);
        controls.getChildren().add(hb4);
        controls.getChildren().add(hb5);
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

        makeControls();
        makeControls2();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
