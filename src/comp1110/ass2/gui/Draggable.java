package comp1110.ass2.gui;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;


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
//    for (int i=0;i<4;i++){
//            Pane node1 = generateNode("aacb");
//            Pane node2 = generateNode("cbaa");
//            Pane node3 = generateNode("acba");
//            Pane node4 = generateNode("baac");
//            Pane node5 = generateNode("aaaa");
//            node1.relocate(800, 60);
//            node2.relocate(870, 60);
//            node3.relocate(940, 60);
//            node4.relocate(1010, 60);
//            node5.relocate(800, 130);
//            draggable(node1);
//            draggable(node2);
//            draggable(node3);
//            draggable(node4);
//            draggable(node5);
//            root.getChildren().addAll(node1,node2,node3,node4,node5);
//        }
//        for (int i=0;i<3;i++){
//            Pane node1 = generateNode("cbcb");
//            Pane node2 = generateNode("bcbc");
//            node1.relocate(870, 130);
//            node2.relocate(940, 130);
//            draggable(node1);
//            draggable(node2);
//            root.getChildren().addAll(node1,node2);
//        }
//        for (int i=0;i<2;i++){
//            Pane node1 = generateNode("cccc");
//            Pane node2 = generateNode("bbbb");
//            Pane node3 = generateNode("dacc");
//            Pane node4 = generateNode("cdac");
//            Pane node5 = generateNode("ccda");
//            Pane node6 = generateNode("accd");
//            Pane node7 = generateNode("dbba");
//            Pane node8 = generateNode("adbb");
//            Pane node9 = generateNode("badb");
//            Pane node10 = generateNode("bbad");
//            Pane node11 = generateNode("ddbc");
//            Pane node12 = generateNode("cddb");
//            Pane node13 = generateNode("bcdd");
//            Pane node14 = generateNode("dbcd");
//            Pane node15 = generateNode("adad");
//            Pane node16 = generateNode("dada");
//            Pane node17 = generateNode("dddd");
//            node1.relocate(1010, 130);
//            node2.relocate(800, 200);
//            node3.relocate(870, 200);
//            node4.relocate(940, 200);
//            node5.relocate(1010, 200);
//            node6.relocate(800, 270);
//            node7.relocate(870, 270);
//            node8.relocate(940, 270);
//            node9.relocate(1010, 270);
//            node10.relocate(800, 340);
//            node11.relocate(870, 340);
//            node12.relocate(940, 340);
//            node13.relocate(1010, 340);
//            node14.relocate(800, 410);
//            node15.relocate(870, 410);
//            node16.relocate(940, 410);
//            node17.relocate(1010, 410);
//            draggable(node1);
//            draggable(node2);
//            draggable(node3);
//            draggable(node4);
//            draggable(node5);
//            draggable(node6);
//            draggable(node7);
//            draggable(node8);
//            draggable(node9);
//            draggable(node10);
//            draggable(node11);
//            draggable(node12);
//            draggable(node13);
//            draggable(node14);
//            draggable(node15);
//            draggable(node16);
//            draggable(node17);
//            root.getChildren().addAll(node1,node2,node3,node4,node5,node6,node7,node8,node9,node10,
//                    node11,node12,node13,node14,node15,node16,node17);
//        }
//    }
//
//    private Pane generateNode(String piece) {
//        Pane node = new StackPane();
//        Viewer.PieceImage piece =new Viewer.PieceImage(0,0,piece);
//        node.getChildren().addAll(piece);
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
