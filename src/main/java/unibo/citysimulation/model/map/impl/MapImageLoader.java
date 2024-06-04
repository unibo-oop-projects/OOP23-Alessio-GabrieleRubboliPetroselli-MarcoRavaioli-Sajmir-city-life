package unibo.citysimulation.model.map.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * Utility class to support MApModel to manage the image in background.
 */
public class MapImageLoader {
    private BufferedImage image;

    /**
     * Constructor to load the image map.
     */
    public MapImageLoader() {
        loadMapImage();
    }

    /**
     * Retrieves the image of the map.
     *
     * @return The BufferedImage object representing the map image.
     */
    protected BufferedImage getImage() {
        return image != null ? image : new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    }

    private void loadMapImage() {
        try {
            final URL imageUrl = MapImageLoader.class.getResource("/unibo/citysimulation/images/mapImage.png");
            if (imageUrl != null) {
                image = ImageIO.read(imageUrl);
            } else {
                throw new IOException("Image file not found");
            }
        } catch (IOException e) {
            handleImageLoadError(e);
        }
    }

    private void handleImageLoadError(final IOException e) {
        JOptionPane.showMessageDialog(null, "Error loading map image: " + e.getMessage(), "Error",
            JOptionPane.ERROR_MESSAGE);
    }
}
