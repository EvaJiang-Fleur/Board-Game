package comp1110.ass2.gui;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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

    @Override
    public void start(Stage stage) throws Exception {
       AnchorPane root=new AnchorPane();
       Scene scene=new Scene(root);
       stage.setScene(scene);
       //set the title
       stage.setTitle("Metro");
       //set the size of the scene
       double Width=1024;
       double Height=768;
       stage.setWidth(Width);
       stage.setHeight(Height);
        //add the title of the game:Metro
        Label label = new Label();
        label.setText("Metro");
        label.setLayoutX(450);
        label.setLayoutY(50);
        label.setFont(new Font("宋体", 50));
        //add the "play" button
        Button btn2 = new Button("Play");
        btn2.setLayoutX(100);
        btn2.setLayoutY(350);
        btn2.setOnAction((ActionEvent event) ->{
            C2 open  = new C2();
            open.start(new Stage());
            stage.hide();

        });
        //add the button"instuctions of the menu
        Button btn1 = new Button("Instructions");
        btn1.setLayoutX(100);
        btn1.setLayoutY(450);
        btn1.setOnAction((ActionEvent event) ->{
                    C1 open  = new C1();
                    open.start(new Stage());
                    stage.hide();
                    });
        //add the "settings" button
        Button btn3 = new Button("Settings");
        btn3.setLayoutX(100);
        btn3.setLayoutY(550);
        //add the"exit "button
        Button btn4 = new Button("Exit");
        btn4.setLayoutX(100);
        btn4.setLayoutY(650);
        //when you click the"exit "button,the scene will close
        btn4.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent ae){
                stage.close();
            }
        });
        //set the background
        Image image=new Image("/comp1110/ass2/gui/assets/background.jpg");

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(Width);
        imageView.setFitHeight(Height);
        root.getChildren().add(imageView);


        root.getChildren().add(label);
        root.getChildren().add(btn2);
        root.getChildren().add(btn1);
        root.getChildren().add(btn3);
        root.getChildren().add(btn4);
        stage.show();


    }
//when you click the "instructions" button ,it comes a new scene
    public class C1 extends Application {
        @Override
        public void start(Stage primaryStage) {
            StackPane root = new StackPane();
            Scene scene = new Scene(root, 1024, 768);
            primaryStage.setTitle("instructions");
            Label label2 = new Label("content of instructions:");
            label2.setFont(new Font("宋体", 20));
            label2.setLayoutX(10);
            label2.setLayoutY(40);
            primaryStage.setScene(scene);
            root.getChildren().add(label2);
            primaryStage.show();
        }
    }
    //when you click on the play button,there will bve a new scene
    //first we need choose the number of the players
    public class C2 extends Application {
        @Override
        public void start(Stage primaryStage) {
            VBox root=new VBox();
            Scene scene = new Scene(root, 1024, 500);
            primaryStage.setTitle("instructions");
            Label label5=new Label("no player selected");
            label5.setLayoutY(100);
            label5.setLayoutY(100);
            MenuItem item1=new MenuItem("Two Players");
            MenuItem item2=new MenuItem("Three Players");
            MenuItem item3=new MenuItem("Four Players");
            MenuItem item4=new MenuItem("Five Players");
            MenuItem item5=new MenuItem("Six Players");
            MenuButton menuButton=new MenuButton("number of players:",null,item1,item2,item3,item4,item5);
            item1.setOnAction(e->{
                    label5.setText("Two Players");
        });
            item2.setOnAction(e->{
                label5.setText("Three Players");
            });
            item3.setOnAction(e->{
                label5.setText("Four Players");
            });
            item4.setOnAction(e->{
                label5.setText("Five Players");
            });
            item5.setOnAction(e->{
                label5.setText("Six Players");
            });

            primaryStage.setScene(scene);

            root.getChildren().add(menuButton);
            root.getChildren().add(label5);
            primaryStage.show();
        }
    }



}
