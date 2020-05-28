package comp1110.ass2.gui;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.awt.*;

//get inspiration from Almas Baimagambetov who create a video about JAVAFX game menu
//the youtube link is https://www.youtube.com/watch?v=PTwpDkUMowk&t=1s
/**
 * @author Yvonne(Xinyi) Zhang
 */

public class Player extends Application {
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

        playerPage player2 = new playerPage("Two Players");
        player2.setOnMouseClicked(event -> {
            MainViewer open  = new MainViewer();
            try {
                open.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            stage.hide();
        });

        playerPage player3 = new playerPage("Three Players");
        player3.setOnMouseClicked(event -> {
            Menu2 open  = new Menu2();
            try {
                open.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            stage.hide();
        });
        playerPage player4 = new playerPage("Four Players");
        player4.setOnMouseClicked(event -> {
            Menu2 open  = new Menu2();
            try {
                open.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            stage.hide();
        });
        playerPage player5 = new playerPage("Five Players");
        player5.setOnMouseClicked(event -> {
            Menu2 open  = new Menu2();
            try {
                open.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            stage.hide();
        });
        playerPage player6 = new playerPage("Six Players");
        player6.setOnMouseClicked(event -> {
            Menu2 open  = new Menu2();
            try {
                open.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            stage.hide();
        });


        Playerbox pP = new Playerbox(player2,player3,player4,player5,player6);


        pP.setTranslateX(390);
        pP.setTranslateY(340);

        root.getChildren().add(pP);

        stage.setTitle("Metro");
        stage.setScene(scene);
        stage.show();




    }

    private static class playerPage extends StackPane{
        public playerPage(String player){
        Rectangle rg= new Rectangle(250,50);
        rg.setFill(Color.valueOf("#fcd4d4"));
        rg.setOpacity(0.8);

        Text text = new Text(player);
        text.setFont(Font.font("Verdana", FontWeight.BOLD,28));
        text.setFill(Color.valueOf("#6e1a00"));
        setAlignment(Pos.CENTER);
        getChildren().addAll(rg,text);


        setOnMousePressed(e ->{
            rg.setFill(Color.BROWN);
            text.setFill(Color.WHITE);
        });

        setOnMouseReleased(e->{
            rg.setFill(Color.valueOf("#fcd4d4"));
            text.setFill(Color.valueOf("#6e1a00"));
        });

        }
        }

    private static class Playerbox extends VBox{
        public Playerbox(playerPage...items){
            getChildren().add(linebt());
            for(playerPage item:items){
                getChildren().addAll(item, linebt());
            }
        }

        private Line linebt(){
            Line line = new Line();
            line.setStroke(Color.valueOf("#731c1c"));
            line.setEndX(250);
            return line;

        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
