import org.opencv.core.*;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.highgui.HighGui;

public class Main {
    public static void main(String[] args) {

        System.load("C:\\Users\\Franklin Lima\\Documents\\opencv\\opencv\\build\\java\\x64\\opencv_java4110.dll");

        CascadeClassifier detector = new CascadeClassifier("haarcascade_frontalface_default.xml");

        VideoCapture camera = new VideoCapture(0);

        if (!camera.isOpened()) {
            System.out.println("Erro ao abrir a câmera!");
            return;
        }

        Mat frame = new Mat();

        while (true) {

            camera.read(frame);

            if (frame.empty()) {
                System.out.println("Erro ao capturar frame!");
                break;
            }

            MatOfRect rostos = new MatOfRect();
            detector.detectMultiScale(frame, rostos);

            for (Rect rect : rostos.toArray()) {
                Imgproc.rectangle(
                        frame,
                        new Point(rect.x, rect.y),
                        new Point(rect.x + rect.width, rect.y + rect.height),
                        new Scalar(0, 255, 0),
                        2
                );
            }

            HighGui.imshow("Webcam - Detecção Facial", frame);

            if (HighGui.waitKey(30) == 27) { // tecla ESC
                break;
            }
        }

        camera.release();
        HighGui.destroyAllWindows();
    }
}