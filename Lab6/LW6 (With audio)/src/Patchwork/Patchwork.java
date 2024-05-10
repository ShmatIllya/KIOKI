package Patchwork;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.imageio.ImageIO;

public class Patchwork {
    public static boolean checkAuthorship(int delta, int n, String originImagePath, String markedImagePath, String secretKey) throws IOException, NoSuchAlgorithmException {
        addWatermark(delta, n, originImagePath, secretKey, "temp.png");
        BufferedImage tempImage = ImageIO.read(new File("temp.png"));
        BufferedImage markedImage = ImageIO.read(new File(markedImagePath));

        if (tempImage.getWidth() != markedImage.getWidth() || tempImage.getHeight() != markedImage.getHeight()) {
            return false;
        }

        for (int x = 0; x < tempImage.getWidth(); x++) {
            for (int y = 0; y < tempImage.getHeight(); y++) {
                if (tempImage.getRGB(x, y) != markedImage.getRGB(x, y)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void addWatermark(int delta, int n, String imagePath, String secretKey, String outputFileName) throws NoSuchAlgorithmException, IOException {
        BufferedImage image = ImageIO.read(new File(imagePath));
        int width = image.getWidth();
        int height = image.getHeight();
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(secretKey.getBytes());

        int[] ai;
        int[] bi;

        for(int i = 0; i < n; i++) {
            ai = new int[]{random.nextInt(width), random.nextInt(height)};
            bi = new int[]{random.nextInt(width), random.nextInt(height)};
            Color[] newColors = getNewBrightness(image, ai, bi, delta);
            changeBrightness(image, ai, bi, newColors);
        }
        ImageIO.write(image, "png", new File(outputFileName));
    }

    private static Color[] getNewBrightness(BufferedImage image, int[] coords_ai, int[] coords_bi, int delta) {
        Color color1 = new Color(image.getRGB(coords_ai[0], coords_ai[1]));
        Color color2 = new Color(image.getRGB(coords_bi[0], coords_bi[1]));
        int red;
        int green;
        int blue;

        // Increase the brightness of the color by the delta value
        red = Math.min(color1.getRed() + delta, 255);
        green = Math.min(color1.getGreen() + delta, 255);
        blue = Math.min(color1.getBlue() + delta, 255);
        Color brighterColor = new Color(red, green, blue);

        // Decrease the brightness of the color by the delta value
        red = Math.max(color2.getRed() - delta, 0);
        green = Math.max(color2.getGreen() - delta, 0);
        blue = Math.max(color2.getBlue() - delta, 0);
        Color darkerColor = new Color(red, green, blue);

        return new Color[]{brighterColor, darkerColor};
    }

    private static void changeBrightness(BufferedImage image, int[] coords_ai, int[] coords_bi, Color[] newColors) {
        image.setRGB(coords_ai[0], coords_ai[1], newColors[0].getRGB());
        image.setRGB(coords_bi[0], coords_bi[1], newColors[1].getRGB());
    }
}
