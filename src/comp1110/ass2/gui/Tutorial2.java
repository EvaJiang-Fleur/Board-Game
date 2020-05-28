package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Tutorial2 extends Application {
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;

//this context is from the rule of metro game
//which is a brief introduction tutorial of this game
//the link of the game is https://tesera.ru/images/items/94746/MetroEN.pdf

    /**
     * @author Yvonne(Xinyi) Zhang
     */
    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane root = new AnchorPane();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        root.setPrefWidth(WIDTH);
        root.setPrefHeight(HEIGHT);
//        imageView.setOnMouseClicked(event -> {
//            Information info  = new Information();
//            try {
//                info.start(new Stage());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            stage.hide();
//        });


        Image rule2 = new Image("/comp1110/ass2/gui/assets/rule2.png");
        ImageView imageView = new ImageView(rule2);
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);
        root.getChildren().add(imageView);
        Rectangle rectangle= new Rectangle(110,50);
        rectangle.setLayoutX(855);
        rectangle.setLayoutY(630);
        rectangle.setFill(Color.BLACK);
        Label label=new Label("Back");
        label.setLayoutX(870);
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
            stage.hide();
        });
        root.getChildren().addAll(rectangle,label);
        stage.setTitle("Metro");
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
