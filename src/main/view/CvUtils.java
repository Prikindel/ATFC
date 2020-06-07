package main.view;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Arrays;

public class CvUtils {

    public static final Scalar COLOR_BLACK  = colorRGB(0, 0, 0);
    public static final Scalar COLOR_WHITE  = colorRGB(255, 255, 255);
    public static final Scalar COLOR_RED    = colorRGB(255, 0, 0);
    public static final Scalar COLOR_BLUE   = colorRGB(0, 0, 255);
    public static final Scalar COLOR_GREEN  = colorRGB(0, 255, 0);
    public static final Scalar COLOR_YELLOW = colorRGB(255, 255, 0);
    public static final Scalar COLOR_GRAY   = colorRGB(128, 128, 128);

    public static Scalar colorRGB (double red, double green, double blue) {
        return new Scalar(blue, green, red);
    }

    public static Scalar colorRGB (java.awt.Color c) {
        return new Scalar(c.getBlue(), c.getGreen(), c.getRed());
    }

    public static Scalar colorRGBA (double red, double green, double blue, double alpha) {
        return new Scalar(blue, green, red, alpha);
    }

    public static Scalar colorRGBA (java.awt.Color c) {
        return new Scalar(c.getBlue(), c.getGreen(), c.getRed(), c.getAlpha());
    }

    public static BufferedImage MatToBufferedImage (Mat m) {
        // 3.1
        if (m == null || m.empty()) return null;

        if (m.depth() != CvType.CV_8U) {
            if (m.depth() == CvType.CV_16U) { // CV_16U => CV_8U
                Mat m_16 = new Mat();
                m.convertTo(m_16, CvType.CV_8U, 255.0 / 65535);
                m = m_16;
            }
            else if (m.depth() == CvType.CV_32F) { // CV_32F => CV_8U
                Mat m_32 = new Mat();
                m.convertTo(m_32, CvType.CV_8U, 255);
                m = m_32;
            }
            else
                return null;
        }
        int type = 0;
        if (m.channels() == 1)
            type = BufferedImage.TYPE_BYTE_GRAY;
        else if (m.channels() == 3)
            type = BufferedImage.TYPE_3BYTE_BGR;
        else if (m.channels() == 4)
            type = BufferedImage.TYPE_4BYTE_ABGR;
        else
            return null;

        byte[] buf = new byte[m.channels() * m.cols() * m.rows()];
        m.get(0,0, buf);
        byte tmp = 0;
        if (m.channels() == 4) { // BGRA => ABGR
            for (int i = 0; i < buf.length; i += 4) {
                tmp = buf[i + 3];
                buf[i + 3] = buf[i + 2];
                buf[i + 2] = buf[i + 1];
                buf[i + 1] = buf[i];
                buf[i] = tmp;
            }
        }

        BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(buf, 0, data, 0, buf.length);
        return image;
    }

    public static Mat BufferedImageToMat (BufferedImage img) {
        // 3.2
        if (img == null) return new Mat();

        int type = 0;
        if (img.getType() == BufferedImage.TYPE_BYTE_GRAY)
            type = CvType.CV_8UC1;
        else if (img.getType() == BufferedImage.TYPE_3BYTE_BGR)
            type = CvType.CV_8UC3;
        else if (img.getType() == BufferedImage.TYPE_4BYTE_ABGR)
            type = CvType.CV_8UC4;
        else return new Mat();

        Mat m = new Mat(img.getHeight(), img.getWidth(), type);
        byte[] data = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        if (type == CvType.CV_8UC1 || type == CvType.CV_8UC3) {
            m.put(0, 0, data);
            return m;
        }
        byte[] buf = Arrays.copyOf(data, data.length);
        byte tmp = 0;
        for (int i = 0; i < buf.length; i += 4) { // ABGR => BGRA
            tmp = buf[i];
            buf[i] = buf[i + 1];
            buf[i + 1] = buf[i + 2];
            buf[i + 2] = buf[i + 3];
            buf[i + 3] = tmp;
        }
        m.put(0,0, buf);
        return m;
    }

}
