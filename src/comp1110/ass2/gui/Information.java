package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//get inspiration from Almas Baimagambetov who create a video about JAVAFX game menu
//the youtube link is https://www.youtube.com/watch?v=PTwpDkUMowk&t=1s
/**
 * @author Yvonne(Xinyi) Zhang
 */

public class Information extends Application {
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;
    private AnchorPane root = new AnchorPane();

//add a private class for title
    private void addTitle() {
        Rectangle rectangle= new Rectangle(325,50);
        rectangle.setLayoutX(350);
        rectangle.setLayoutY(320);
        rectangle.setFill(Color.BEIGE);
        Label label=new Label("Author information");
        label.setLayoutX(350);
        label.setLayoutY(325);
        label.setFont(Font.font("Verdana", FontWeight.BOLD,30));
        label.setTextFill(Color.BLACK);


        root.getChildren().addAll(rectangle,label);
    }

    //add private class for background image
    private void addbackgroundImage(){
        Image image=new Image("/comp1110/ass2/gui/assets/background.jpg");

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);
        root.getChildren().add(imageView);

    }

    private void exit() {
        Rectangle rectangle= new Rectangle(110,50);
        rectangle.setLayoutX(700);
        rectangle.setLayoutY(600);
        rectangle.setFill(Color.BLACK);
        Label label=new Label("Exit");
        label.setLayoutX(720);
        label.setLayoutY(605);
        label.setFont(Font.font("Verdana", FontWeight.BOLD,30));
        label.setTextFill(Color.WHITE);
        label.setOnMouseClicked(event -> {
            Menu1 open  = new Menu1();
            try {
                open.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        root.getChildren().addAll(rectangle,label);
    }



    //put all the class together
    private Parent createContent() {
        addbackgroundImage();
        addTitle();
        exit();

        ManuItem author1 = new ManuItem("Xinyi Zhang u6976740");
        ManuItem author2 = new ManuItem("Ruiqiao Jiang u6818746");
        ManuItem author3 = new ManuItem("Xinyao Wang u6609958");

        ManuBox menu = new ManuBox(author1,author2,author3);

        menu.setTranslateX(280);
        menu.setTranslateY(390);

        root.getChildren().add(menu);

        return root;
    }
    private static class ManuItem extends StackPane {
        public ManuItem(String name){
            Rectangle rectangle= new Rectangle(450,50);
            rectangle.setFill(Color.valueOf("#fcd4d4"));
            rectangle.setOpacity(0.8);

            Text text = new Text(name);
            text.setFont(Font.font("Verdana", FontWeight.BOLD,30));
            text.setFill(Color.valueOf("#6e1a00"));
            setAlignment(Pos.CENTER);
            getChildren().addAll(rectangle,text);

        }

    }

    private static class ManuBox extends VBox {
        public ManuBox(ManuItem... items){
            getChildren().add(linebt());
            for(ManuItem item:items){
                getChildren().addAll(item, linebt());
            }
        }

        private Line linebt(){
            Line line = new Line();
            line.setStroke(Color.valueOf("#731c1c"));
            line.setEndX(450);
            return line;

        }
    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Information");
        addbackgroundImage();
        addTitle();
        Scene scene = new Scene(createContent());
        stage.setScene(scene);
        stage.show();

    }
}
