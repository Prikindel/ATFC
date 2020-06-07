package present;

import model.Cascade;
import model.DataModel;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.objdetect.CascadeClassifier;
import view.CvUtils;

public class Analysis {

    /**
     * @param img       Матрица изображения
     * @param cascade   ВЫбранный файл для каскада хаара
     * @return          Преобразованная матрица с обнаруженными на ней объектами
     */
    public static Mat detector(Mat img, Cascade cascade) {
        CascadeClassifier cascadeClassifier = DataModel.getCascadeClassifier(cascade);

        Mat gray = Converion.matToGray(img);

        MatOfRect numbers = new MatOfRect();
        assert cascadeClassifier != null;
        cascadeClassifier.detectMultiScale(gray, numbers);

        Converion.addRectangleToMat(img, numbers, CvUtils.COLOR_RED);

        return img;
    }

}
