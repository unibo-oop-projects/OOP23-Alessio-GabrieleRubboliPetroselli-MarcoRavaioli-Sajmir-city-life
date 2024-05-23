package unibo.citysimulation.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class MapImageLoader {
    private BufferedImage image;

    public MapImageLoader() {
        loadMapImage();
    }

    /**
     * Retrieves the image of the map.
     *
     * @return The BufferedImage object representing the map image.
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Loads the map image from the resources.
     */
    private void loadMapImage() {
        try {
            URL imageUrl = getClass().getResource("/unibo/citysimulation/image3.png");
            if (imageUrl != null) {
                image = ImageIO.read(imageUrl);
            } else {
                throw new IOException("Image file not found");
            }
        } catch (IOException e) {
            handleImageLoadError(e);
        }
    }

    /**
     * Handles errors that occur during image loading.
     *
     * @param e The IOException instance representing the error.
     */
    private void handleImageLoadError(IOException e) {
        JOptionPane.showMessageDialog(null, "Error loading map image: " + e.getMessage(), "Error",
            JOptionPane.ERROR_MESSAGE);
    }
}
