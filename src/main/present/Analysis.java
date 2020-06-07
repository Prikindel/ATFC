package main.present;

import main.model.Cascade;
import main.model.DataModel;
import main.view.CvUtilsFX;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import main.view.CvUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Analysis {

    /**
     * Детектирует и отмечает на входном изображении объекты
     * по выбранному каскаду.
     *
     * @param img       Матрица изображения
     * @param cascade   Выбранный файл для каскада хаара
     * @return          MatOfRect. Найденные объекты
     */
    public static MatOfRect detector(Mat img, Cascade cascade, Scalar color) {
        CascadeClassifier cascadeClassifier = DataModel.getCascadeClassifier(cascade);

        Mat gray = Converion.matToGray(img);

        MatOfRect numbers = new MatOfRect();
        assert cascadeClassifier != null;
        cascadeClassifier.detectMultiScale(gray, numbers);

        Converion.addRectangleToMat(img, numbers, color);

        return numbers;
    }

    public static void carsDetectorOnImage(Mat img, List carsList, List numbersList) {
        MatOfRect cars = detector(img, Cascade.CAR, CvUtils.COLOR_RED);
        ArrayList<MatOfRect> numbers = new ArrayList<>();
        carsList.add(cars.toList());
        for (Rect r : cars.toList()) {
            MatOfRect bufRects = detector(img.submat(r), Cascade.NUMBER_CAR, CvUtils.COLOR_BLUE);
            numbers.add(bufRects);
            if(!bufRects.toList().isEmpty()) {
                System.out.println("123 " + bufRects.toList().get(0));
                String numberString = getNumberToCar(img.submat(bufRects.toList().get(0)));
                Converion.addNumberToCar(img, r, bufRects.toList().get(0), numberString);
            }
        }
        numbersList.add(numbers);
    }

    public static String getNumberToCar(Mat img) {
        StringBuilder numberString = new StringBuilder();
        ArrayList<Rect> rects = new ArrayList<>();

        Mat output = imageToTextString(img, rects);
        DataModel.saveImage(output, String.valueOf(System.currentTimeMillis()));

        for (Rect r : rects) {
            numberString.append(imgToString(img.submat(r)));
        }
        return numberString.toString();
    }

    public static Mat imageToTextString(Mat img, List<Rect> outRects) {
        Mat imgGray = Converion.matToGray(img);;
        Mat imgThresh = new Mat();
        Imgproc.threshold(imgGray, imgThresh, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
        Mat imgErode = new Mat();
        Imgproc.erode(imgThresh, imgErode, new Mat());

        // Получение контуров
        ArrayList<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(imgErode, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_NONE);

        Mat output = img.clone();

        int i = 0;
        for (Mat contour : contours) {

            Rect r = Imgproc.boundingRect(contour);
            System.out.println("[" + i + "] - " + Arrays.toString(hierarchy.get(0, i)));
            System.out.println(r.toString());

            if (hierarchy.get(0, i)[3] == 0) {
            //if (r.width >= 50 && r.width <= 100 && r.height >= 50 && r.height <= 100) {
                Imgproc.rectangle(output, new Point(r.x, r.y),
                        new Point(r.x + r.width, r.y + r.height),
                        CvUtils.COLOR_RED);

                Imgproc.putText(output, i + "", new Point(r.x, r.y),
                        Imgproc.FONT_HERSHEY_PLAIN | Imgproc.FONT_ITALIC, 2,
                        CvUtils.COLOR_GREEN);

                outRects.add(r);
            }

            i++;
        }

        return output;
    }

    public static String imgToString(Mat img) {
        String symb = "c";
        /*
        Распознавание
         */
        return symb;
    }
}
