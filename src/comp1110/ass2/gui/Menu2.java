package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;



public class Menu2 extends Application  {
        public static void main(String[] args) {
                launch(args);
        }
        @Override
        public void start(Stage stage) throws Exception {
                AnchorPane root=new AnchorPane();
                Scene scene=new Scene(root);
                stage.setScene(scene);
                Image image=new Image("/comp1110/ass2/gui/assets/background.jpg");


                //set the title
                stage.setTitle("Metro");
                //set the size of the scene
                double Width=1024;
                double Height=768;
                stage.setWidth(Width);
                stage.setHeight(Height);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(Width);
                imageView.setFitHeight(Height);
                //set the checkbox
                CheckBox box1=new CheckBox("Computer");
                CheckBox box2=new CheckBox("Computer");
                CheckBox box3=new CheckBox("Computer");
                CheckBox box4=new CheckBox("Computer");
                CheckBox box5=new CheckBox("Computer");
                CheckBox box6=new CheckBox("Computer");
                //add the label of player's number
                Label label1 = new Label("Player 1");
                box1.setLayoutX(200);
                box1.setLayoutY(100);
                label1.setLayoutX(50);
                label1.setLayoutY(100);
                Label label2 = new Label("Player 2");
                box2.setLayoutX(200);
                box2.setLayoutY(200);
                label2.setLayoutX(50);
                label2.setLayoutY(200);
                Label label3 = new Label("Player 3");
                box3.setLayoutX(200);
                box3.setLayoutY(300);
                label3.setLayoutX(50);
                label3.setLayoutY(300);
                Label label4 = new Label("Player 4");
                box4.setLayoutX(200);
                box4.setLayoutY(400);
                label4.setLayoutX(50);
                label4.setLayoutY(400);
                Label label5 = new Label("Player 5");
                box5.setLayoutX(200);
                box5.setLayoutY(500);
                label5.setLayoutX(50);
                label5.setLayoutY(500);
                Label label6 = new Label("Player 6");
                box6.setLayoutX(200);
                box6.setLayoutY(600);
                label6.setLayoutX(50);
                label6.setLayoutY(600);
                Button bt1=new Button("Cancel");
                bt1.setLayoutX(200);
                bt1.setLayoutY(650);
                bt1.setOnAction((ActionEvent event) ->{
                        Menu1 open  = new Menu1();
                        try {
                                open.start(new Stage());
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                        stage.hide();
                });
                //add the button"start" and when you click on start,it will switch to a new scene "MainViewer"
                Button bt2=new Button("Start");
                bt2.setLayoutX(600);
                bt2.setLayoutY(650);
                bt2.setOnAction((ActionEvent event) ->{
                        MainViewer open  = new MainViewer();
                        try {
                                open.start(new Stage());
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                        stage.hide();
                });
                // add the choicebox so that you can choose the number of players
                Label label8=new Label("number of players :");
                label8.setLayoutX(50);
                label8.setLayoutY(50);
                ChoiceBox<String> choiceBox=new ChoiceBox<>();
                choiceBox.getItems().addAll("one player","two players","three players","four players","five players","six players");
                choiceBox.setLayoutX(200);
                choiceBox.setLayoutY(50);
                //use choicebox to choose the difficulty
                ChoiceBox<String> choiceBox2=new ChoiceBox<>();
                choiceBox2.getItems().addAll("novice","average","agressive");
                choiceBox2.setLayoutX(400);
                choiceBox2.setLayoutY(100);
                ChoiceBox<String> choiceBox3=new ChoiceBox<>();
                choiceBox3.getItems().addAll("novice","average","agressive");
                choiceBox3.setLayoutX(400);
                choiceBox3.setLayoutY(200);
                ChoiceBox<String> choiceBox4=new ChoiceBox<>();
                choiceBox4.getItems().addAll("novice","average","agressive");
                choiceBox4.setLayoutX(400);
                choiceBox4.setLayoutY(300);
                ChoiceBox<String> choiceBox5=new ChoiceBox<>();
                choiceBox5.getItems().addAll("novice","average","agressive");
                choiceBox5.setLayoutX(400);
                choiceBox5.setLayoutY(400);
                ChoiceBox<String> choiceBox6=new ChoiceBox<>();
                choiceBox6.getItems().addAll("novice","average","agressive");
                choiceBox6.setLayoutX(400);
                choiceBox6.setLayoutY(500);
                ChoiceBox<String> choiceBox7=new ChoiceBox<>();
                choiceBox7.getItems().addAll("novice","average","agressive");
                choiceBox7.setLayoutX(400);
                choiceBox7.setLayoutY(600);


                root.getChildren().add(imageView);
                root.getChildren().add(box1);
                root.getChildren().add(box2);
                root.getChildren().add(box3);
                root.getChildren().add(box4);
                root.getChildren().add(box5);
                root.getChildren().add(box6);
                root.getChildren().add(label1);
                root.getChildren().add(label2);
                root.getChildren().add(label3);
                root.getChildren().add(label4);
                root.getChildren().add(label5);
                root.getChildren().add(label6);
                root.getChildren().add(label8);

                root.getChildren().add(choiceBox);
                root.getChildren().add(choiceBox2);
                root.getChildren().add(choiceBox3);
                root.getChildren().add(choiceBox4);
                root.getChildren().add(choiceBox5);
                root.getChildren().add(choiceBox6);
                root.getChildren().add(choiceBox7);
                root.getChildren().add(bt1);
                root.getChildren().add(bt2);

                stage.show();

        }

}

