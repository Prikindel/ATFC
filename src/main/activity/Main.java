package main.activity;

import javafx.application.Application;
import javafx.stage.Stage;

import main.model.DataFiles;
import main.model.DataModel;
import main.view.CvUtilsFX;
import org.opencv.core.Core;
import main.present.Analysis;
import org.opencv.core.Mat;

import java.util.ArrayList;

public class Main extends Application {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Mat img = DataModel.getDataMat(DataFiles.IMAGE_1);
        Analysis.carsDetectorOnImage(img, new ArrayList(), new ArrayList());
        CvUtilsFX.showImageInNewWindow(CvUtilsFX.getSceneImage(img), "Result");
    }
}
