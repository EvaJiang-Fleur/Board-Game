package comp1110.ass2;

import comp1110.ass2.gui.Menu1;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

import org.junit.Test;

//it is hard to test javafx, therefore, I did some research about the test for Javafx, beased on the answer in
//the stackOverflow https://stackoverflow.com/questions/18429422/basic-junit-test-for-javafx-8, here is the easiest way to approach
//I did some improvement based on the original code, I am going to do more improvement about javafx based on more
//code that I have written in the later stage.

public class Menu1Test {

    @Test
    public void testA() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                new JFXPanel(); // Initializes the JavaFx Platform
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            new Menu1().start(new Stage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(10000); // Time to use the app, with out this, the thread
        // will be killed before you can tell.
    }

}
