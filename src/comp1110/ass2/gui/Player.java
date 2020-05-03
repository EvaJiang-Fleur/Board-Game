package comp1110.ass2.gui;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

//get inspiration from Almas Baimagambetov who create a video about JAVAFX game menu
//the youtube link is https://www.youtube.com/watch?v=PTwpDkUMowk&t=1s

public class Player extends Application {
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;

    private Parent playercontent(){
        Pane root = new Pane();
        root.setPrefSize(WIDTH,HEIGHT);

        Image image=new Image("/comp1110/ass2/gui/assets/background.jpg");

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);
        root.getChildren().add(imageView);


        Playerbox pP = new Playerbox(
                new Player.playerPage("Two Players"),
                new Player.playerPage("Three Players"),
                new Player.playerPage("Four Players"),
                new Player.playerPage("Five Players"),
                new Player.playerPage("Six Players"));


        pP.setTranslateX(390);
        pP.setTranslateY(340);

        root.getChildren().add(pP);


        return root;

    }


    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(playercontent());
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
