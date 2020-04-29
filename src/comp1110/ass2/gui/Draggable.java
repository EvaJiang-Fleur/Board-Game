package comp1110.ass2.gui;

import com.sun.glass.ui.View;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class Draggable {
    //This class is a temporary class, will be added into Viewer after finished
    //The purpoes of the ode is to make some pictures on the viewer draggable


//    class PieceImage extends ImageView {
//        PieceImage(double x, double y, String piece) {
//            setImage(new Image(Viewer.class.getResource(URI_BASE + piece+".jpg").toString(),SQUARE_SIZE,SQUARE_SIZE,true,true));
//            setLayoutX(x);
//            setLayoutY(y);
//        }
//    }
//
//    void makeBoard() {
////     (use a loop to add all the tiles)
//    Pane node = generateNode(piecestring);
//        node.relocate(xnumber, ynumber);
//    draggable(node);
//        root.getChildren().addAll(node);
//    }
//
//    private Pane generateNode(String piece) {
//        Pane node = new StackPane();
//        Viewer.PieceImage piece1 =new Viewer.PieceImage(0,0,piece);
//        node.getChildren().addAll(piece1);
//        return node;
//    }

    private static class Position {
        double x;
        double y;
    }

    public void draggable(Node node) {
        final Position pos = new Position();
        node.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            pos.x = event.getX();
            pos.y = event.getY();
        });
        node.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            double x = node.getLayoutX()+ event.getX() - pos.x;
            double y = node.getLayoutY()+ event.getY() - pos.y;
//            if (x>220&x<700&&y>70&&y<550)
//            {x=((int)(x-160)/60)*60+160;
//                y=((int)(y-10)/60)*60+10;}
            node.relocate(x, y);
        });
    }

}
