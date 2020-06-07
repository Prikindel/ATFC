package main.model;

import org.opencv.core.Mat;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

/**
 *
 */

public abstract class MainModel {

    static String getCascadePath(Cascade cascade) {
        return null;
    }

    static CascadeClassifier getCascadeClassifier(Cascade cascade) {
        return null;
    }

    static String getDataPath(DataFiles data) {
        return null;
    }

    static Mat getDataMat(DataFiles data) {
        return null;
    }

    static VideoCapture getDataVideo(DataFiles data) {
        return null;
    }

    static boolean saveImage(Mat img, String name) {
        return false;
    }

}
