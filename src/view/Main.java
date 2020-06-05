package view;

import javafx.application.Application;
import javafx.stage.Stage;

import org.opencv.core.Core;

public class Main extends Application {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
