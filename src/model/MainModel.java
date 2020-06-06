package model;

import org.opencv.core.Mat;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

/**
 *
 */

enum Cascade {
    CAR("myhaar.xml"),
    NUMBER_CAR("haarcascade_russian_plate_number.xml"),
    FLOW("cars.xml");
    private final String path;
    Cascade(String path) {
        this.path = path;
    }
    public String getPath() {
        return "//haarCascades//" + this.path;
    }
}

/**
 *
 */

enum DataFiles {
    VIDEO("cars.mp4"),
    IMAGE_POLICE("1.jpg"),
    IMAGE_1("8.jpg"),
    IMAGE_2("17.jpg"),
    IMAGE_3("18.jpg"),
    IMAGE_4("21.jpg");
    private final String path;
    DataFiles(String path) {
        this.path = path;
    }

    public String getPath() {
        return "//dataFiles//" + this.path;
    }
}

/**
 *
 */

public interface MainModel {

    String getCascadePath(Cascade cascade);

    CascadeClassifier getCascadeClassifier(Cascade cascade);

    String getDataPath(DataFiles data);

    Mat getDataMat(DataFiles data);

    VideoCapture getDataVideo(DataFiles data);

    boolean saveImage(Mat img, String name);

}
