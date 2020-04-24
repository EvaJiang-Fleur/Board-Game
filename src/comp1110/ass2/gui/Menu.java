package comp1110.ass2.gui;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;




public class Menu extends Application {
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;
    //add the background of the game into the interface of the manu
    private void background(){
        ImageView imageView = new ImageView(new Image(getClass().getResource("background.jpg").toExternalForm()));
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);
        //getChildren().add(imageView);
    }

    //the title of the game:Metro
    private void title(){
       // Title title = new Title("Metro: Queen games");


    }

    private void manuitem(){

    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}

