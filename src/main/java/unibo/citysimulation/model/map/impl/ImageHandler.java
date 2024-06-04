package unibo.citysimulation.model.map.impl;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Optional;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * Utility class to support MapModel and MapPanel to manage and handle the map image.
 */
public class ImageHandler implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient BufferedImage image;

    public ImageHandler() {
        loadImage();
    }

    /**
     * Loads the image map from resources.
     */
    public void loadImage() {
        try {
            final URL imageUrl = ImageHandler.class.getResource("/unibo/citysimulation/images/mapImage.png");
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
     * Gets a defensive copy of the current image.
     *
     * @return an optional containing a defensive copy of the current image
     */
    public Optional<BufferedImage> getImage() {
        return Optional.ofNullable(createImageDefensiveCopy(image));
    }

    /**
     * Sets the image with a defensive copy.
     *
     * @param image the image to set
     */
    public void setImage(final BufferedImage image) {
        this.image = createImageDefensiveCopy(image);
    }

    private BufferedImage createImageDefensiveCopy(final BufferedImage original) {
        if (original == null) {
            return null;  // Return null if the original is null
        }
        final BufferedImage copy = new BufferedImage(
            original.getWidth(),
            original.getHeight(),
            original.getType()
        );
        final Graphics2D g = copy.createGraphics();
        g.drawImage(original, 0, 0, null);
        g.dispose();
        return copy;
    }

    private void handleImageLoadError(final IOException e) {
        JOptionPane.showMessageDialog(null, "Error loading map image: " + e.getMessage(), "Error",
            JOptionPane.ERROR_MESSAGE);
    }
}
