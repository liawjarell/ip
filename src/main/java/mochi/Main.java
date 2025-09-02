package mochi;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mochi.gui.MainWindow;


/**
 * Main class for running the application.
 */
public class Main extends Application {

    // Remember to create an overloaded constructor

    private Mochi mochi = new Mochi("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMochi(mochi);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
