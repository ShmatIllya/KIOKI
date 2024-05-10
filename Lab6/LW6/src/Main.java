import LSB.LSB;
import Patchwork.Patchwork;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        String inputAudioFile = "Alarm05.wav";
        String outputAudioFile = "Stega_Alarm05.wav";
        String message = "Hello uiuy";

        try {
            LSB.encode(inputAudioFile, message, outputAudioFile);
            LSB.decode(outputAudioFile);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        Patchwork.addWatermark(543, 20000, "original_image.png", "secretKey", "marked_image.png");
        if (Patchwork.checkAuthorship(54, 20000, "original_image.png", "marked_image.png", "secretKey")) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }

    }
}
