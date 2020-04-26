package comp1110.ass2.gui;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.awt.*;

import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;


public class Menu extends Application {

    public static void main(String[] args) {
        launch(args);
    }




    //add the background of the game into the interface of the menu
  /*private void background() {
        ImageView imageView = new ImageView(new Image(getClass().getResource("background.jpg").toExternalForm()));
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);
        root.getChildren().add(imageView);
    }

*/



    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane root=new AnchorPane();
        Scene scene=new Scene(root);
       stage.setScene(scene);
       stage.setTitle("Metro");
       double Width=1024;
       double Height=768;
       stage.setWidth(Width);
       stage.setHeight(Height);
        //the title of the game:Metro
        Label label = new Label();
        label.setText("Metro");
        label.setLayoutX(450);
        label.setLayoutY(50);
        label.setFont(new Font("宋体", 30));


        root.getChildren().add(label);
        stage.show();


    }




}
