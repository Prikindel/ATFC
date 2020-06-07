package present;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import view.CvUtils;

public class Converion {

    /**
     * Преобразует копию исходной матрицы в оттенки серого.
     *
     * @param img   Матрица изображения
     * @return      Изображение в оттенках серого
     */
    public static Mat matToGray(Mat img) {
        Mat gray = new Mat();
        Imgproc.cvtColor(img, gray, Imgproc.COLOR_BGR2GRAY);
        return gray;
    }

    /**
     * Добавляет на копию исходной матрицы переданные прямоугольники.
     *
     * @param img           Матрица изображения
     * @param rectangles    Матрица объектов Rect
     * @param color         Цвет отмечаемых объектов в формате Scalar
     * @return              Изображение с отмеченными на ней прямоугольниками
     */
    public static Mat addRectangleToMat(Mat img, MatOfRect rectangles, Scalar color) {
        Mat imgCopy = img.clone();
        for (Rect r : rectangles.toList()) {
            Imgproc.rectangle(imgCopy, new Point(r.x, r.y),
                    new Point(r.x + r.width, r.y + r.height),
                    color);
        }
        return imgCopy;
    }

}
