package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;



import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;



//get inspiration from Almas Baimagambetov who create a video about JAVAFX game menu
//the youtube link is https://www.youtube.com/watch?v=PTwpDkUMowk&t=1s
//make the improvement based on ruiqiao jiang's code
/**
 * @author Yvonne(Xinyi) Zhang
 * discuss with ruiqiao jiang
 */
public class Menu1 extends Application {
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane root = new AnchorPane();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        root.setPrefWidth(WIDTH);
        root.setPrefHeight(HEIGHT);

        Image image=new Image("/comp1110/ass2/gui/assets/background.jpg");

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);
        root.getChildren().add(imageView);


        //set the bottom for interface
        ManuItem exit= new ManuItem("EXIT");
        exit.setOnMouseClicked(e-> {
            System.out.println("Calling Platform.exit()");
            Platform.exit();
        });
        //jump to player page
        ManuItem play = new ManuItem("Play");
        play.setOnMouseClicked(event -> {
            MainViewer open  = new MainViewer();
            try {
                open.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            stage.hide();
        });

        //jump to information page
        ManuItem information = new ManuItem("Info");
        information.setOnMouseClicked(event -> {
            Information info  = new Information();
            try {
                info.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            stage.hide();
        });

        ManuItem tutorial = new ManuItem("Tutorial");
        tutorial.setOnMouseClicked(event -> {
            Tutorial info  = new Tutorial();
            try {
                info.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            stage.hide();
        });

        ManuBox menu = new ManuBox(play,information,tutorial,exit);

        menu.setTranslateX(415);
        menu.setTranslateY(340);

        root.getChildren().add(menu);


        stage.setTitle("Metro");


        stage.show();


    }

    private static class ManuItem extends StackPane{
        public ManuItem(String name){
            Stop[] stop= new Stop[]{
                    new Stop(0, Color.valueOf("#f78e8b")),
                    new Stop(0.3,Color.valueOf("#f2716d")),
                    new Stop(0.5,Color.valueOf("#d6413c")),
                    new Stop(0.8,Color.valueOf("#f2716d")),
                    new Stop(1,Color.valueOf("#f78e8b"))};
            LinearGradient lg = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stop);
            Rectangle rectangle= new Rectangle(200,50);
            rectangle.setFill(Color.valueOf("#fcd4d4"));
            rectangle.setOpacity(0.8);

            Text text = new Text(name);
            text.setFont(Font.font("Verdana", FontWeight.BOLD,30));
            text.setFill(Color.valueOf("#6e1a00"));
            setAlignment(Pos.CENTER);
            getChildren().addAll(rectangle,text);

            setOnMouseEntered(e ->{
                rectangle.setFill(lg);
            });

            setOnMouseExited(e ->{
                rectangle.setFill(Color.ROSYBROWN);
            });

            setOnMousePressed(e ->{
                rectangle.setFill(Color.BROWN);
                text.setFill(Color.WHITE);
            });

            setOnMouseReleased(e->{
                rectangle.setFill(lg);
                text.setFill(Color.valueOf("#6e1a00"));
            });


        }

    }

    private static class ManuBox extends VBox{
        public ManuBox(ManuItem...items){
            getChildren().add(linebt());
            for(ManuItem item:items){
                getChildren().addAll(item, linebt());
            }
        }

        private Line linebt(){
            Line line = new Line();
            line.setStroke(Color.valueOf("#731c1c"));
            line.setEndX(200);
            return line;

        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}