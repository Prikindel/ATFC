package model;

import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import java.io.File;

public class Data implements MainModel {

    private static final String DIR_SAVEIMAGE = "//saveImage//";

    @Override
    public String getCascadePath(Cascade cascade) {
        return cascade.getPath();
    }

    @Override
    public CascadeClassifier getCascadeClassifier(Cascade cascade) {
        CascadeClassifier cascadeClassifier = new CascadeClassifier(cascade.getPath());

        if (cascadeClassifier.empty()) {
            System.out.println("Не загружен классификатор");
            return null;
        }
        return cascadeClassifier;
    }

    @Override
    public String getDataPath(DataFiles data) {
        return data.getPath();
    }

    @Override
    public Mat getDataMat(DataFiles data) {
        Mat img = Imgcodecs.imread(data.getPath());
        if (img.empty()) {
            System.out.println("Не удалось загрузить изображение");
            return null;
        }
        return img;
    }

    @Override
    public VideoCapture getDataVideo(DataFiles data) {
        VideoCapture video = new VideoCapture();
        video.open(data.getPath());
        if (!video.isOpened()) {
            System.out.println("Не удалось открыть видео");
            return null;
        }
        return video;
    }

    @Override
    public boolean saveImage(Mat img, String name) {
        final File dir = new File(DIR_SAVEIMAGE);

        if(!dir.exists()) {
            if(!dir.mkdir()) {
                System.out.println("Каталог для хранения изображений не удалось создать");
            }
        }

        name = DIR_SAVEIMAGE + name;
        boolean st = Imgcodecs.imwrite(name, img,
                new MatOfInt(Imgcodecs.IMWRITE_PNG_COMPRESSION, 0));
        if (!st) {
            System.out.println("Не удалось сохранить изображение");
            return false;
        } else {
            System.out.println("Изображение успешно сохранено");
        }
        return true;
    }
}
