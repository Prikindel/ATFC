package main.present;

import main.view.CvUtils;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

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
     * Добавляет на исходную матрицу переданные прямоугольники.
     *
     * @param img           Матрица изображения
     * @param rectangles    Матрица объектов Rect
     * @param color         Цвет отмечаемых объектов в формате Scalar
     */
    public static void addRectangleToMat(Mat img, MatOfRect rectangles, Scalar color) {
        for (Rect r : rectangles.toList()) {
            Imgproc.rectangle(img, new Point(r.x, r.y),
                    new Point(r.x + r.width, r.y + r.height),
                    color);
        }
    }

    public static void addNumberToCar(Mat img, Rect car, Rect number, String text) {
        Mat num = img.submat(number);
        img.put(car.x + car.width - number.width,
                car.y + car.height - number.height / 2,
                num.get(0,0));
        Imgproc.putText(img,
                text,
                new Point(
                        car.x + car.width - number.width,
                        car.y + car.height + number.height / 2
                ),
                Imgproc.FONT_HERSHEY_COMPLEX, 2, CvUtils.COLOR_GREEN);
    }

}
